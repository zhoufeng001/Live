package com.zf.live.dao.mybatisgenrator.plugin;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * mybatis generator插件
 * 用于mysql分页代码生成
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月14日 上午3:29:44
 */
public final class MySQLPaginationPlugin extends PluginAdapter {

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
	    IntrospectedTable introspectedTable) {        
	addPage(topLevelClass, introspectedTable, "page");       
	return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }    

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
	    IntrospectedTable introspectedTable) {
	XmlElement page = new XmlElement("if");
	page.addAttribute(new Attribute("test", "page != null"));
	page.addElement(new TextElement("limit #{page.begin} , #{page.length}"));
	element.addElement(page);      
	return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }   

    /**
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
	    String name) {
	topLevelClass.addImportedType(new FullyQualifiedJavaType("com.zf.live.dao.vo.Page"));
	CommentGenerator commentGenerator = context.getCommentGenerator();
	Field field = new Field();
	field.setVisibility(JavaVisibility.PROTECTED);
	field.setType(new FullyQualifiedJavaType("com.zf.live.dao.vo.Page"));
	field.setName(name);
	commentGenerator.addFieldComment(field, introspectedTable);
	topLevelClass.addField(field);        char c = name.charAt(0);
	String camel = Character.toUpperCase(c) + name.substring(1);
	Method method = new Method();
	method.setVisibility(JavaVisibility.PUBLIC);
	method.setName("set" + camel);
	method.addParameter(new Parameter(new FullyQualifiedJavaType("com.zf.live.dao.vo.Page"), name));
	method.addBodyLine("this." + name + "=" + name + ";");
	commentGenerator.addGeneralMethodComment(method, introspectedTable);
	topLevelClass.addMethod(method);
	method = new Method();
	method.setVisibility(JavaVisibility.PUBLIC);
	method.setReturnType(new FullyQualifiedJavaType("com.zf.live.dao.vo.Page"));
	method.setName("get" + camel);
	method.addBodyLine("return " + name + ";");
	commentGenerator.addGeneralMethodComment(method, introspectedTable);
	topLevelClass.addMethod(method);
    }    

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {        
	return true;
    }
}