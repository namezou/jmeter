package org.apache.jmeter.functions;



import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RandomGet extends AbstractFunction {
    //自定义function的描述
    private static final List<String> desc = new LinkedList<String>();
    static {
        desc.add("测试自定义方法");
    }
    //function名称
    private static final String KEY = "__RandomGet";

    //保存传入参数的值
    private Object[] values;


    /**
     * JMeter会将上次运行的SampleResult和当前的Sampler作为参数传入到该方法里，返回值就是在运行该function后得到的值，
     * 以String类型返回。该方法如果操作了非线程安全的对象（比如文件），则需要将对该方法进行线程同步保护
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        //取传进来的参数
        int requMin = new Integer(((CompoundVariable)values[0]).execute().trim());
        int requMax = new Integer(((CompoundVariable)values[1]).execute().trim());
        int targetLength = new Integer(((CompoundVariable)values[2]).execute().trim());

        if(requMax-requMin < 1){
            return null;
        }else if(requMax-requMin <targetLength){
            return null;
        }
        int target = targetLength;
        List list = new ArrayList();
        List requList = new ArrayList();
        for (int i = requMin; i <= requMax; i++) {
            requList.add(i);
        }

        for (int i = 0; i < targetLength; i++) {
            // 取出一个随机角标
            int r = (int) (Math.random() * requList.size());
            list.add(requList.get(r));
            // 移除已经取过的值
            requList.remove(r);
        }

        return  list.toString().replaceAll("(?:\\[|null|\\]| +)", "");
    }

    /**
     * 这个方法在用于传递用户在执行过程当中传入的实际参数值。该方法在function没有参数情况下也会被调用。
     * 一般该方法传入的参数会被保存在类内全局变量里，并被后面调用的execute方法中使用到。
     * @param collection
     * @throws InvalidVariableException
     */
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        values = collection.toArray();
    }

    /**
     * 这个就是function的名字。JMeter的命名规则是在方法名前面加入双下划线"__"。比如"__GetEven"，function的名字跟实现该类的类名应该一致，
     * 而且该名字应该以static final的方式在实现类中定义好，避免在运行的时候更改它
     * @return
     */
    public String getReferenceKey() {
        return KEY;
    }

    /**
     *   最后在你的实现类中还需要提供一个方法来告诉JMeter关于你实现的function的描述。
     * @return
     */
    public List<String> getArgumentDesc() {
        return desc;
    }


}
