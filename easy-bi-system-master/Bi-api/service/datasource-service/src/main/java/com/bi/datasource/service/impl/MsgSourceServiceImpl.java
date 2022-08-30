package com.bi.datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bi.datasource.entity.MsgSource;
import com.bi.datasource.entity.dto.QueryDto;
import com.bi.datasource.entity.vo.CHMetaVo;
import com.bi.servicebase.entity.dto.DataSourceCreateDto;
import com.bi.datasource.entity.vo.DataVo;
import com.bi.datasource.entity.vo.QueryVo;
import com.bi.datasource.service.DataSourceService;
import com.bi.datasource.service.MsgSourceService;
import com.bi.datasource.mapper.MsgSourceMapper;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.exception.GlobalException;
import com.bi.servicebase.uitls.json.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Panda
* @description 针对表【tb_msg_source】的数据库操作Service实现
* @createDate 2022-08-21 14:22:26
*/
@Service
public class MsgSourceServiceImpl extends ServiceImpl<MsgSourceMapper, MsgSource>
    implements MsgSourceService{

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private List<CHMetaVo> metadata;

    private Map<String,List<Object>> keyValues;

    @Override
    public DataSource saveMetadata(DataSourceCreateDto dataSourceCreateVo) {

        try {
            metadata = baseMapper.getMetadata(dataSourceCreateVo.getDbName(),
                    dataSourceCreateVo.getTableName());
        }catch (Exception e){
            throw new GlobalException(201,"该数据源不存在!");
        }

        String schemaStr = jsonUtil.write(metadata);
        DataSource dataSource = new DataSource();
        BeanUtils.copyProperties(dataSourceCreateVo,dataSource);
        dataSource.setTableSchema(schemaStr);
        dataSourceService.save(dataSource);
//        Boolean delete = stringRedisTemplate.delete("data_source");
//        if (!delete){
//            throw new GlobalException(201,"元信息缓存清除失败");
//        }
        return dataSource;
    }

    @Override
    public QueryVo queryByRule(QueryDto queryDto) {
        String dataSetId = queryDto.getDataSetId();
        long start = System.currentTimeMillis();
        String tableName = dataSourceService.selectTableName(dataSetId);
        List<Map<String,Object>> list = baseMapper.queryByRule(queryDto,tableName);
        long stop = System.currentTimeMillis();

        QueryVo queryVo = new QueryVo();
        queryVo.setCost(stop - start);

        keyValues = new HashMap<>();
        for (Map<String,Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            entries.forEach(entry -> {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (!keyValues.containsKey(key)){
                    keyValues.put(key,new ArrayList<Object>());
                }
                List<Object> values = keyValues.get(key);
                values.add(value);
            });
        }

        List<DataVo> dataVos = buildDataVos();
        queryVo.setData(dataVos);
        return queryVo;
    }

    private List<DataVo> buildDataVos(){
        List<DataVo> dataVos= new ArrayList<>();
        keyValues.entrySet().forEach(entry -> {
            DataVo dataVo = new DataVo();
            dataVo.setKey(entry.getKey());
            dataVo.setValue(entry.getValue());
            dataVos.add(dataVo);
        });
        return dataVos;
    }
}




