package com.bi.main.service.feign;

import com.bi.commonutils.result.Result;
import com.bi.main.entity.vo.QueryVo;
import com.bi.servicebase.entity.DataSource;
import com.bi.servicebase.entity.dto.DataSourceCreateDto;
import com.bi.servicebase.entity.dto.QueryDto;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Panda
 * @data 2022/8/27 15:08
 */
@Component
@FeignClient("datasource-service")
public interface DataInfoFeignService {

    @PostMapping("/saveMetadata")
    public Result<DataSource> saveMetadata(@ApiParam("元信息添加信息")
                                                     @RequestBody DataSourceCreateDto dataSourceCreateDto);

    @PostMapping("/query")
    public Result<QueryVo> query(@ApiParam("用户需求")@RequestBody QueryDto queryDto);
}
