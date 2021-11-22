package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Status Model")
public class Status {

    @ApiModelProperty(value = "Unique identifier of status")
    private int id;

    @ApiModelProperty(value = "title of status")
    private String status;

    public Status() {

    }

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
