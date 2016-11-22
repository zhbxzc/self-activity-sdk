package com.self.activity.sdk.constants;

/**
 * emr-app/emr-domain中用到的常量
 * @author itw_huomb
 */
public interface EmrConstant
{
	/**
	 * 病历模板、段落模板的停用状态
	 */
	public static final Integer STATUS_DISABLE = 1;

	/**
	 * 病历模板、段落模板的启用状态
	 */
	public static final Integer STATUS_ENABLE = 0;

	/**
	 * 初步诊断id
	 */
	public static final String FIRST_DIAGNOSIS = "7";

	/**
	 * 删除标志：删除的
	 */
	public static final boolean DELETED = true;

	/**
	 * 删除标志：在使用的
	 */
	public static final boolean NOT_DELETED = false;

	/**
	 * mongodb存储病历的collection
	 */
	public static final String DOC = "doc";
	/**
	 * mongodb存储病历模板的collection
	 */
	public static final String DOC_TEMPLATE = "doc_template";
}
