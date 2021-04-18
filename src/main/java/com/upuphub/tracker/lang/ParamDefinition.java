package com.upuphub.tracker.lang;

/**
 * 参数属性定义属性
 *
 * @author Inspiration S.P.A Leo
 **/
public class ParamDefinition {

    /**
     * Handler的ParamType定义属性
     */
    private ParamType paramType;

    /**
     * 参数属性的位置
     */
    private Integer index;

    /**
     * 获取ParamType定义属性位置
     *
     * @return 产生属性产生
     */
    public ParamType getParamType() {
        return paramType;
    }

    /**
     * 数据处理器方法的设置参数类型、必选
     *
     * @param paramTypeIn 参数数据输入产生
     */
    public void setParamType(ParamType paramTypeIn) {
        this.paramType = paramTypeIn;
    }

    /**
     * 获取入参参数位置,主要获取Input参数的Index
     *
     * @return Input参数的Index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 设置Input参数的位置
     *
     * @param indexIn input参数的位置
     */
    public void setIndex(Integer indexIn) {
        this.index = indexIn;
    }
}
