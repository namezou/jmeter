package org.apache.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RandomGetIdCardForFifteen extends AbstractFunction {

    private final static List<String> desc = new LinkedList<String>();
    static{
        desc.add("输入参数0返回男性17位身份证号，1返回17位女性身份证号码");
        desc.add("输入参数yyyyMMdd，0/1，返回出生日期为yyyyMMdd的男性或女性17身份证");
    }

    private static final String KEY = "__RandomGetIdCardForFifteen";
    private Object[] values;

    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        String idCard15;
        String birth;
        String male;

        if (values.length>1){
            birth = new String(((CompoundVariable)values[0]).execute().trim());
            male = new String(((CompoundVariable)values[1]).execute().trim());
            idCard15= StringUtils.getIdNo(birth,male);
        }else{
            male = new String(((CompoundVariable)values[0]).execute().trim());
            idCard15= StringUtils.getIdNo(male);
        }

        return idCard15;
    }

    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        values = collection.toArray();
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }
}
