package com.zf.live.dao.mybatisgenrator.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * 用于生成Service类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月15日 下午6:01:51
 */
public class ZFServicePlugin extends PluginAdapter {

    /**
     * Service文件存放目录
     */
    private String serviceDir ;
    private String servicePackage ;

    @Override
    public boolean validate(List<String> warn) {
	return true ;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
	    IntrospectedTable introspectedTable) {
	if(serviceDir == null){
	    return null ;
	}
	if(!generator(introspectedTable)){
	    return null ;
	}

	GeneratedJavaFile baseServiceInterface = genrateBaseServiceInterface(introspectedTable) ;

	String javaModelType = introspectedTable.getBaseRecordType() ;
	String serviceType = getBaseServiceName(javaModelType);
	FullyQualifiedJavaType interfaceType = new FullyQualifiedJavaType(serviceType);
	GeneratedJavaFile serviceClass = genrateServiceClass(introspectedTable, interfaceType);


	List<GeneratedJavaFile> list = new ArrayList<GeneratedJavaFile>() ;
	list.add(baseServiceInterface) ;
	list.add(serviceClass) ;

	return list;
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
	    IntrospectedTable introspectedTable) {
	if( serviceDir == null){
	    return null ;
	}
	if(!generator(introspectedTable)){
	    return null ;
	}


	return super.contextGenerateAdditionalXmlFiles(introspectedTable);
    }

    private boolean generator(IntrospectedTable introspectedTable){
	String generateExt = introspectedTable.getTableConfiguration().getProperties().getProperty("generateService") ;
	if("true".equals(generateExt)){
	    return true ;
	}
	return false ;
    }

    @Override
    public void setProperties(Properties properties) {
	super.setProperties(properties);
	serviceDir = properties.getProperty("serviceDir");
	servicePackage = properties.getProperty("package");
    }

    private GeneratedJavaFile genrateBaseServiceInterface(IntrospectedTable introspectedTable){
	String javaModelType = introspectedTable.getBaseRecordType() ;
	String serviceType = getBaseServiceName(javaModelType);
	FullyQualifiedJavaType type = new FullyQualifiedJavaType(serviceType);
	Interface interfaze = new Interface(type);
	interfaze.setVisibility(JavaVisibility.PUBLIC);

	StringBuilder doc = new StringBuilder() ;
	doc.append("/**").append("\r\n") ;
	doc.append(" * by is_zhoufeng@163.com " + String.format("%1$tF %1$tT", System.currentTimeMillis())).append("\r\n") ;
	doc.append(" */") ;

	interfaze.addJavaDocLine(doc.toString());;

	GeneratedJavaFile baseServiceInterface = new GeneratedJavaFile(interfaze,
		serviceDir,  
		context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING) ,
		context.getJavaFormatter()) ;
	return baseServiceInterface ;
    }

    private GeneratedJavaFile genrateServiceClass(IntrospectedTable introspectedTable,FullyQualifiedJavaType interfaceType){

	CommentGenerator commentGenerator = context.getCommentGenerator();

	String javaModelType = introspectedTable.getBaseRecordType() ;
	String serviceType = getServiceName(javaModelType);
	FullyQualifiedJavaType type = new FullyQualifiedJavaType(serviceType);

	TopLevelClass serviceClass = new TopLevelClass(type);
	serviceClass.setVisibility(JavaVisibility.PUBLIC);
	commentGenerator.addJavaFileComment(serviceClass);

	serviceClass.addSuperInterface(interfaceType); 
	serviceClass.addImportedType(interfaceType);

	Field field = new Field();
	field.setVisibility(JavaVisibility.PRIVATE);
	FullyQualifiedJavaType extType = new FullyQualifiedJavaType(
		introspectedTable.getMyBatis3JavaMapperType() + "Ext");
	field.setType(extType);
	field.setName(getFieldName(introspectedTable.getMyBatis3JavaMapperType()));

	serviceClass.addImportedType("org.springframework.beans.factory.annotation.Autowired");
	field.addAnnotation("@Autowired");

	serviceClass.addField(field);
	serviceClass.addImportedType(field.getType());



	GeneratedJavaFile baseServiceInterface = new GeneratedJavaFile(serviceClass,
		serviceDir,  
		context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING) ,
		context.getJavaFormatter()) ;
	return baseServiceInterface ;
    }


    private String getBaseServiceName(String javaModelType){
	int lastDIndex = javaModelType.lastIndexOf(".");
	String modelName = null ;
	if(lastDIndex > 0){
	    modelName = javaModelType.substring(lastDIndex + 1) ;
	}else if(lastDIndex == 0){
	    modelName = javaModelType ;
	}else{
	    return null ;
	}
	return String.format("%s.%sService", servicePackage , modelName );
    }

    private String getServiceName(String javaModelType){
	int lastDIndex = javaModelType.lastIndexOf(".");
	String modelName = null ;
	if(lastDIndex > 0){
	    modelName = javaModelType.substring(lastDIndex + 1) ;
	}else if(lastDIndex == 0){
	    modelName = javaModelType ;
	}else{
	    return null ;
	}
	return String.format("%s.impl.%sServiceImpl", servicePackage , modelName );
    }

    private String getFieldName(String extMapperType){
	int lastDIndex = extMapperType.lastIndexOf(".");
	String fieldName = null ;
	if(lastDIndex > 0){
	    fieldName = extMapperType.substring(lastDIndex + 1) ;
	}else if(lastDIndex == 0){
	    fieldName = extMapperType ;
	}else{
	    return null ;
	}
	fieldName = fieldName.substring(0 , 1).toLowerCase() + fieldName.substring(1);
	return fieldName ;
    }


}
