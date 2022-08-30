package com.bi.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_msg_source
 */
@TableName(value ="tb_msg_source")
@Data
public class MsgSource implements Serializable {
    /**
     * 消息发送时间
     */
    @TableField(value = "msg_time")
    private Object msgTime;

    /**
     * 发送人昵称
     */
    @TableField(value = "sender_name")
    private Object senderName;

    /**
     * 发送人账号
     */
    @TableField(value = "sender_account")
    private Object senderAccount;

    /**
     * 发送人性别
     */
    @TableField(value = "sender_sex")
    private Object senderSex;

    /**
     * 发送人ip地址
     */
    @TableField(value = "sender_ip")
    private Object senderIp;

    /**
     * 发送人操作系统
     */
    @TableField(value = "sender_os")
    private Object senderOs;

    /**
     * 发送人手机型号
     */
    @TableField(value = "sender_phonetype")
    private Object senderPhonetype;

    /**
     * 发送人网络类型
     */
    @TableField(value = "sender_network")
    private Object senderNetwork;

    /**
     * 发送人的GPS定位
     */
    @TableField(value = "sender_gps")
    private Object senderGps;

    /**
     * 接收人昵称
     */
    @TableField(value = "receiver_name")
    private Object receiverName;

    /**
     * 接收人IP
     */
    @TableField(value = "receiver_ip")
    private Object receiverIp;

    /**
     * 接收人账号
     */
    @TableField(value = "receiver_account")
    private Object receiverAccount;

    /**
     * 接收人操作系统
     */
    @TableField(value = "receiver_os")
    private Object receiverOs;

    /**
     * 接收人手机型号
     */
    @TableField(value = "receiver_phonetype")
    private Object receiverPhonetype;

    /**
     * 接收人网络类型
     */
    @TableField(value = "receiver_network")
    private Object receiverNetwork;

    /**
     * 接收人的GPS定位
     */
    @TableField(value = "receiver_gps")
    private Object receiverGps;

    /**
     * 接收人性别
     */
    @TableField(value = "receiver_sex")
    private Object receiverSex;

    /**
     * 消息类型
     */
    @TableField(value = "msg_type")
    private Object msgType;

    /**
     * 双方距离
     */
    @TableField(value = "distance")
    private Object distance;

    /**
     * 消息内容
     */
    @TableField(value = "message")
    private Object message;

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
        MsgSource other = (MsgSource) that;
        return (this.getMsgTime() == null ? other.getMsgTime() == null : this.getMsgTime().equals(other.getMsgTime()))
            && (this.getSenderName() == null ? other.getSenderName() == null : this.getSenderName().equals(other.getSenderName()))
            && (this.getSenderAccount() == null ? other.getSenderAccount() == null : this.getSenderAccount().equals(other.getSenderAccount()))
            && (this.getSenderSex() == null ? other.getSenderSex() == null : this.getSenderSex().equals(other.getSenderSex()))
            && (this.getSenderIp() == null ? other.getSenderIp() == null : this.getSenderIp().equals(other.getSenderIp()))
            && (this.getSenderOs() == null ? other.getSenderOs() == null : this.getSenderOs().equals(other.getSenderOs()))
            && (this.getSenderPhonetype() == null ? other.getSenderPhonetype() == null : this.getSenderPhonetype().equals(other.getSenderPhonetype()))
            && (this.getSenderNetwork() == null ? other.getSenderNetwork() == null : this.getSenderNetwork().equals(other.getSenderNetwork()))
            && (this.getSenderGps() == null ? other.getSenderGps() == null : this.getSenderGps().equals(other.getSenderGps()))
            && (this.getReceiverName() == null ? other.getReceiverName() == null : this.getReceiverName().equals(other.getReceiverName()))
            && (this.getReceiverIp() == null ? other.getReceiverIp() == null : this.getReceiverIp().equals(other.getReceiverIp()))
            && (this.getReceiverAccount() == null ? other.getReceiverAccount() == null : this.getReceiverAccount().equals(other.getReceiverAccount()))
            && (this.getReceiverOs() == null ? other.getReceiverOs() == null : this.getReceiverOs().equals(other.getReceiverOs()))
            && (this.getReceiverPhonetype() == null ? other.getReceiverPhonetype() == null : this.getReceiverPhonetype().equals(other.getReceiverPhonetype()))
            && (this.getReceiverNetwork() == null ? other.getReceiverNetwork() == null : this.getReceiverNetwork().equals(other.getReceiverNetwork()))
            && (this.getReceiverGps() == null ? other.getReceiverGps() == null : this.getReceiverGps().equals(other.getReceiverGps()))
            && (this.getReceiverSex() == null ? other.getReceiverSex() == null : this.getReceiverSex().equals(other.getReceiverSex()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getDistance() == null ? other.getDistance() == null : this.getDistance().equals(other.getDistance()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMsgTime() == null) ? 0 : getMsgTime().hashCode());
        result = prime * result + ((getSenderName() == null) ? 0 : getSenderName().hashCode());
        result = prime * result + ((getSenderAccount() == null) ? 0 : getSenderAccount().hashCode());
        result = prime * result + ((getSenderSex() == null) ? 0 : getSenderSex().hashCode());
        result = prime * result + ((getSenderIp() == null) ? 0 : getSenderIp().hashCode());
        result = prime * result + ((getSenderOs() == null) ? 0 : getSenderOs().hashCode());
        result = prime * result + ((getSenderPhonetype() == null) ? 0 : getSenderPhonetype().hashCode());
        result = prime * result + ((getSenderNetwork() == null) ? 0 : getSenderNetwork().hashCode());
        result = prime * result + ((getSenderGps() == null) ? 0 : getSenderGps().hashCode());
        result = prime * result + ((getReceiverName() == null) ? 0 : getReceiverName().hashCode());
        result = prime * result + ((getReceiverIp() == null) ? 0 : getReceiverIp().hashCode());
        result = prime * result + ((getReceiverAccount() == null) ? 0 : getReceiverAccount().hashCode());
        result = prime * result + ((getReceiverOs() == null) ? 0 : getReceiverOs().hashCode());
        result = prime * result + ((getReceiverPhonetype() == null) ? 0 : getReceiverPhonetype().hashCode());
        result = prime * result + ((getReceiverNetwork() == null) ? 0 : getReceiverNetwork().hashCode());
        result = prime * result + ((getReceiverGps() == null) ? 0 : getReceiverGps().hashCode());
        result = prime * result + ((getReceiverSex() == null) ? 0 : getReceiverSex().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getDistance() == null) ? 0 : getDistance().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgTime=").append(msgTime);
        sb.append(", senderName=").append(senderName);
        sb.append(", senderAccount=").append(senderAccount);
        sb.append(", senderSex=").append(senderSex);
        sb.append(", senderIp=").append(senderIp);
        sb.append(", senderOs=").append(senderOs);
        sb.append(", senderPhonetype=").append(senderPhonetype);
        sb.append(", senderNetwork=").append(senderNetwork);
        sb.append(", senderGps=").append(senderGps);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverIp=").append(receiverIp);
        sb.append(", receiverAccount=").append(receiverAccount);
        sb.append(", receiverOs=").append(receiverOs);
        sb.append(", receiverPhonetype=").append(receiverPhonetype);
        sb.append(", receiverNetwork=").append(receiverNetwork);
        sb.append(", receiverGps=").append(receiverGps);
        sb.append(", receiverSex=").append(receiverSex);
        sb.append(", msgType=").append(msgType);
        sb.append(", distance=").append(distance);
        sb.append(", message=").append(message);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}