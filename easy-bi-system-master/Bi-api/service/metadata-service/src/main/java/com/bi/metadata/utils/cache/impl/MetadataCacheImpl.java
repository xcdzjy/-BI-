package com.bi.metadata.utils.cache.impl;

import com.bi.servicebase.entity.DataSource;
import com.bi.metadata.service.DataSourceService;
import com.bi.metadata.utils.cache.MetadataCache;
import com.bi.servicebase.uitls.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Panda
 * @date 2022/8/19 20:48
 */

@Component
public class MetadataCacheImpl  {

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JsonUtil jsonUtil;

//    @PostConstruct
//    public void init(){
//        List<DataSource> list = dataSourceService.getAll();
//        String value = jsonUtil.write(list);
//        stringRedisTemplate.opsForValue().set(METADATA_KEY,value);
//
//    }
//
//    public void refresh(){
//        init();
//    }
//
//    public List<DataSource> getAllDataSource(){
//        String value = stringRedisTemplate.opsForValue().get(METADATA_KEY);
//        if (StringUtils.isEmpty(value)){
//            refresh();
//            value = stringRedisTemplate.opsForValue().get(METADATA_KEY);
//        }
//        return jsonUtil.read(value,DataSource.class);
//    }

}
