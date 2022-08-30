package com.bi.servicebase.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName data_source
 */
@TableName(value ="data_source")
@Data
public class DataSource implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 数据源名称
     */
    @TableField(value = "data_source_name")
    private String dataSourceName;

    /**
     * 数据源类型
     */
    @TableField(value = "data_source_type")
    private String dataSourceType;

    /**
     * 数据库名称
     */
    @TableField(value = "db_name")
    private String dbName;

    /**
     * 数据表名称
     */
    @TableField(value = "table_name")
    private String tableName;

    /**
     * 数据表元信息
     */
    @TableField(value = "table_schema")
    private String tableSchema;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DataSource other = (DataSource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDataSourceName() == null ? other.getDataSourceName() == null : this.getDataSourceName().equals(other.getDataSourceName()))
            && (this.getDataSourceType() == null ? other.getDataSourceType() == null : this.getDataSourceType().equals(other.getDataSourceType()))
            && (this.getDbName() == null ? other.getDbName() == null : this.getDbName().equals(other.getDbName()))
            && (this.getTableName() == null ? other.getTableName() == null : this.getTableName().equals(other.getTableName()))
            && (this.getTableSchema() == null ? other.getTableSchema() == null : this.getTableSchema().equals(other.getTableSchema()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDataSourceName() == null) ? 0 : getDataSourceName().hashCode());
        result = prime * result + ((getDataSourceType() == null) ? 0 : getDataSourceType().hashCode());
        result = prime * result + ((getDbName() == null) ? 0 : getDbName().hashCode());
        result = prime * result + ((getTableName() == null) ? 0 : getTableName().hashCode());
        result = prime * result + ((getTableSchema() == null) ? 0 : getTableSchema().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataSourceName=").append(dataSourceName);
        sb.append(", dataSourceType=").append(dataSourceType);
        sb.append(", dbName=").append(dbName);
        sb.append(", tableName=").append(tableName);
        sb.append(", tableSchema=").append(tableSchema);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}