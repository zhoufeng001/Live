package com.zf.live.dao.mybatisgenrator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * mybatis generator插件
 * 用于给生产的pojo对象的字段加上注释
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月14日 上午3:35:39
 */
public class ZFFieldExplainPlugin extends PluginAdapter{

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		String doc = getFieldDoc(introspectedColumn) ;
		field.getJavaDocLines().clear();
		field.getJavaDocLines().add(doc);
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
				introspectedTable, modelClassType);
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		String doc = getFieldDoc(introspectedColumn) ;
		method.getJavaDocLines().clear();
		method.getJavaDocLines().add(doc);
		return super.modelGetterMethodGenerated(method, topLevelClass,
				introspectedColumn, introspectedTable, modelClassType);
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		method.getJavaDocLines().clear(); 
		return super.modelSetterMethodGenerated(method, topLevelClass,
				introspectedColumn, introspectedTable, modelClassType);
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		topLevelClass.getJavaDocLines().clear(); 
		StringBuilder doc = new StringBuilder() ;
		doc.append("/**").append("\r\n");
		doc.append(" * ").append("tableName{" + introspectedTable.getTableConfiguration().getTableName() + "}").append("\r\n");
		doc.append(" * by is_zhoufeng@163.com " + String.format("%1$tF %1$tT", System.currentTimeMillis())).append("\r\n");
		doc.append(" */");
		topLevelClass.getJavaDocLines().add(doc.toString());
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	private String getFieldDoc(IntrospectedColumn introspectedColumn ){
		StringBuilder doc = new StringBuilder() ;
		doc.append("/**").append("\r\n");
		doc.append("     * ").append(introspectedColumn.getRemarks()).append("  "); 
		doc.append("\r\n");
		doc.append("     * ").append("column{" + introspectedColumn.getActualColumnName() + "},jdbcType{"+introspectedColumn.getJdbcTypeName()+"}").append("\r\n");
		doc.append("     */");
		return doc.toString() ;
	}



}
