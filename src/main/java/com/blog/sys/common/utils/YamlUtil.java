package com.blog.sys.common.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @ProjectName:    blog
 * @Package:        com.yk.blog.common.utils
 * @ClassName:      YamlUtil
 * @Description:    yml文件配置处理工具类
 * @Author:         yanakai@126.com
 * @CreateDate:     2019/9/26 23:22
 * @UpdateUser:     yanakai@126.com
 * @UpdateDate:     2019/9/26 23:22
 * @UpdateRemark:   更新内容
 * @Version:        1.0
 */

public class YamlUtil{
    public static Map<?, ?> loadYaml(String fileName) throws FileNotFoundException{
        InputStream in = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
        return StringUtils.isNotEmpty(fileName) ? (LinkedHashMap<?, ?>) new Yaml().load(in) : null;
    }

    public static void dumpYaml(String fileName, Map<?, ?> map) throws IOException
    {
        if (StringUtils.isNotEmpty(fileName))
        {
            FileWriter fileWriter = new FileWriter(YamlUtil.class.getResource(fileName).getFile());
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yaml.dump(map, fileWriter);
        }
    }

    public static Object getProperty(Map<?, ?> map, Object qualifiedKey)
    {
        if (map != null && !map.isEmpty() && qualifiedKey != null)
        {
            String input = String.valueOf(qualifiedKey);
            if (!"".equals(input))
            {
                if (input.contains("."))
                {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    return getProperty((Map<?, ?>) map.get(left), right);
                }
                else if (map.containsKey(input))
                {
                    return map.get(input);
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void setProperty(Map<?, ?> map, Object qualifiedKey, Object value)
    {
        if (map != null && !map.isEmpty() && qualifiedKey != null)
        {
            String input = String.valueOf(qualifiedKey);
            if (!input.equals(""))
            {
                if (input.contains("."))
                {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    setProperty((Map<?, ?>) map.get(left), right, value);
                }
                else
                {
                    ((Map<Object, Object>) map).put(qualifiedKey, value);
                }
            }
        }
    }
}