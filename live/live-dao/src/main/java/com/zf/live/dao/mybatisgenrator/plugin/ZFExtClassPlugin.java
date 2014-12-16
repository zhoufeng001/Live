package com.zf.live.dao.mybatisgenrator.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * mybatis generator 插件
 * 用于生成扩展接口与扩展mapper文件
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月14日 上午3:28:46
 */
public class ZFExtClassPlugin extends PluginAdapter{

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
			IntrospectedTable introspectedTable) {

		if(!generator(introspectedTable)){
			return null ;
		}

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getMyBatis3JavaMapperType() + "Ext");

		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
				introspectedTable.getMyBatis3JavaMapperType() );
		interfaze.addSuperInterface(fqjt);
		interfaze.addImportedType(fqjt);

		StringBuilder doc = new StringBuilder() ;
		doc.append("/**").append("\r\n") ;
		doc.append(" * 该类用于扩展" + introspectedTable.getMyBatis3JavaMapperType() + "接口").append("\r\n") ;
		doc.append(" * by is_zhoufeng@163.com " + String.format("%1$tF %1$tT", System.currentTimeMillis())).append("\r\n") ;
		doc.append(" */") ;

		interfaze.addJavaDocLine(doc.toString());;


		GeneratedJavaFile extJava = new GeneratedJavaFile(interfaze,
				context.getJavaModelGeneratorConfiguration().getTargetProject(),  
				context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING) ,
				context.getJavaFormatter()) ;

		List<GeneratedJavaFile> list = new ArrayList<GeneratedJavaFile>() ;
		list.add(extJava) ;
		return list;
	}


	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
			IntrospectedTable introspectedTable) {


		if(!generator(introspectedTable)){
			return null ;
		}


		Document document = new Document(
				XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
				XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);

		XmlElement root = new XmlElement("mapper"); 
		document.setRootElement(root);

		root.addAttribute(new Attribute("namespace", introspectedTable.getMyBatis3SqlMapNamespace() + "Ext"));

		root.addElement(new TextElement("<!--")); 

		String mapperType =  introspectedTable.getMyBatis3JavaMapperType()  ;
		StringBuilder sb = new StringBuilder();
		sb.append("用于扩展" + mapperType + "接口").append("\r\n");
		sb.append("可通过" + mapperType + ".BaseResultMap \r\n和" + mapperType + ".Base_Column_List对其进行引用").append("\r\n");
		sb.append("by is_zhoufeng@163.com " );
		sb.append(String.format("%1$tF %1$tT", System.currentTimeMillis()));
		sb.append('.');
		root.addElement(new TextElement(sb.toString()));
		root.addElement(new TextElement("-->")); 

		String intfFileName = introspectedTable.getMyBatis3XmlMapperFileName() ;
		String fileName = intfFileName.replace(".", "Ext.") ;

		GeneratedXmlFile gxf = new GeneratedXmlFile(document,fileName,
				context.getSqlMapGeneratorConfiguration().getTargetPackage(), 
				context.getSqlMapGeneratorConfiguration().getTargetProject(), 
				false, context.getXmlFormatter());

		List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>(1);
		answer.add(gxf);

		return answer;
	}

	private boolean generator(IntrospectedTable introspectedTable){
		String generateExt = introspectedTable.getTableConfiguration().getProperties().getProperty("generateExt") ;
		if("true".equals(generateExt)){
			return true ;
		}
		return false ;
	}
}
