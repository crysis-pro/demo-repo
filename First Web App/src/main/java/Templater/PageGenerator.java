package Templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Andrey on 4/16/2016.
 */
public class PageGenerator {
    private static final String TEMPLATE_PATH = "res/templates";
    private static PageGenerator instance;
    private final Configuration cfg;

    private PageGenerator(){
        cfg = new Configuration();
    }
    public static PageGenerator instance(){
        if(instance == null)
            instance = new PageGenerator();
        return instance;
    }

    public String getPage(String fileName, Map<String,Object> data){
        Writer stream = new StringWriter();
        try{
            Template template = cfg.getTemplate(TEMPLATE_PATH + File.separator + fileName);
            template.process(data, stream);
        }catch (TemplateException e){
            e.printStackTrace();
        }
        catch (IOException  e){
            e.printStackTrace();
        }
        return stream.toString();
    }

}
