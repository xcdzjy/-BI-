package com.bi.metadata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bi.servicebase.entity.DataSet;
import com.bi.servicebase.entity.vo.RuleVo;
import com.bi.metadata.service.DataSetService;
import com.bi.metadata.mapper.DataSetMapper;
import com.bi.servicebase.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author Panda
* @description 针对表【data_set】的数据库操作Service实现
* @createDate 2022-08-16 22:18:45
*/
@Service
public class DataSetServiceImpl extends ServiceImpl<DataSetMapper, DataSet>
    implements DataSetService{

    private QueryWrapper<DataSet> queryWrapper;

    @Override
    public String create(DataSet dataSet) {
        int insert = baseMapper.insert(dataSet);
        if (insert <= 0){
            throw new GlobalException(201,"数据集创建失败");
        }

        return dataSet.getId();
    }


    @Override
    public List<DataSet> listByRules(RuleVo ruleVo) {

        List<DataSet> dataSets = baseMapper.listByRules(ruleVo);
        return dataSets;
    }


    @Override
    public DataSet info(String dataSetId) {
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",dataSetId);
        return baseMapper.selectOne(queryWrapper);

    }

    @Override
    public List<DataSet> myList(String username) {
        return baseMapper.myList(username);
    }

    @Override
    public Long countByRule(RuleVo ruleVo) {
        return baseMapper.countByRule(ruleVo);
    }
}




