package com.example.interfazconodoo.Modelos;

import java.sql.Timestamp;

public class Paperformat {

    private Integer id;
    private Integer page_height;
    private Integer page_width;
    private Integer hader_spacing;
    private Integer dpi;
    private Integer create_uid;
    private Integer write_uid;
    private String name;
    private String format;
    private String orientation;
    private Boolean default_;
    private Boolean header_line;
    private Boolean disable_shrinking;
    private Timestamp create_date;
    private Timestamp write_date;
    private Double margin_top;
    private Double margin_bottom;
    private Double margin_left;
    private Double margin_right;

    void Paperformat(Integer id, String name, String format, String orientation, Double margin_top, Double margin_bottom, Double margin_left, Double margin_right) {

        this.id = id;
        this.name = name;
        this.format = format;
        this.orientation = orientation;
        this.margin_top = margin_top;
        this.margin_bottom = margin_bottom;
        this.margin_left = margin_left;
        this.margin_right = margin_right;


    }

    void Paperformat(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage_height() { return page_height; }

    public void setPage_height(Integer page_height) { this.page_height = page_height; }

    public Integer getPage_width() { return page_width; }

    public void setPage_width(Integer page_width) { this.page_width = page_width; }

    public Integer getHader_spacing() { return hader_spacing; }

    public void setHader_spacing(Integer hader_spacing) { this.hader_spacing = hader_spacing; }

    public Integer getDpi() { return dpi; }

    public void setDpi(Integer dpi) { this.dpi = dpi; }

    public Integer getCreate_uid() { return create_uid; }

    public void setCreate_uid(Integer create_uid) { this.create_uid = create_uid; }

    public Integer getWrite_uid() { return write_uid; }

    public void setWrite_uid(Integer write_uid) { this.write_uid = write_uid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFormat() { return format; }

    public void setFormat(String format) { this.format = format; }

    public String getOrientation() { return orientation; }

    public void setOrientation(String orientation) { this.orientation = orientation; }

    public Boolean getDefault_() { return default_; }

    public void setDefault_(Boolean default_) { this.default_ = default_; }

    public Boolean getHeader_line() { return header_line; }

    public void setHeader_line(Boolean header_line) { this.header_line = header_line; }

    public Boolean getDisable_shrinking() { return disable_shrinking; }

    public void setDisable_shrinking() { this.disable_shrinking = disable_shrinking; }

    public Timestamp getCreate_date() { return create_date; }

    public void setCreate_date(Timestamp create_date) { this.create_date = create_date; }

    public Timestamp getWrite_date() { return write_date; }

    public void setWrite_date(Timestamp write_date) { this.write_date = write_date; }

    public Double getMargin_top() { return margin_top; }

    public void setMargin_top(Double margin_top) { this.margin_top = margin_top; }

    public Double getMargin_bottom() { return margin_bottom; }

    public void setMargin_bottom(Double margin_bottom) { this.margin_bottom = margin_bottom; }

    public Double getMargin_left() { return margin_left; }

    public void setMargin_left(Double margin_left) { this.margin_left = margin_left; }

    public Double getMargin_right() { return margin_right; }

    public void setMargin_right(Double margin_right) { this.margin_right = margin_right; }



}
