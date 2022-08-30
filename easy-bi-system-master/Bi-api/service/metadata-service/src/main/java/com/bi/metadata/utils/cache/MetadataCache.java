package com.bi.metadata.utils.cache;

import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.cache.BaseCache;

import java.util.List;

/**
 * @author Panda
 * @data 2022/8/20 10:44
 */
public interface MetadataCache extends BaseCache {
    String METADATA_KEY = "data_source";

    List<DataSource> getAllDataSource();
}
