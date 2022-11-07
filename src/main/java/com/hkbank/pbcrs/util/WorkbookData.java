package com.hkbank.pbcrs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkbookData {

	// private static final Logger log =
	// LogManager.getLogger(WorkbookData.class);

	private Map<String, List<Map<String, String>>> dataTable = new HashMap<String, List<Map<String, String>>>();

	@Override
	public String toString() {
		return this.dataTable.toString();
	}

	public List<Map<String, String>> getDataTable(String tableName) {
		return this.dataTable.get(tableName);
	}

	public int getDataTableCount(String key) {
		if (key == null) {
			return 0;
		}

		// 防止配置时出现前后空格的情况
		// 非表达式的情况不处理掉空白字符，原始返回
		String trimedKey = key.trim();
		if (trimedKey.startsWith("${") && trimedKey.endsWith("}")) {
			String el = trimedKey.substring(2, trimedKey.length() - 1);
			String[] keypath = el.split("\\.");
			// 暂时仅支持"${TABLE_NAME.FIELD_NAME}"这一种模式
			if (keypath == null || keypath.length <= 1) {
				return 0;
			}
			String tableName = keypath[0];

			if (!dataTable.containsKey(tableName)) {
				return 0;
			}
			List<Map<String, String>> data = dataTable.get(tableName);
			if (data == null || data.isEmpty()) {
				return 0;
			} else {
				return data.size();
			}
		} else {
			// 不是表达式，则直接取输入值，长度为1
			return 1;
		}
	}

	public String getSystemValue(String key) {
		if (key == null) {
			return null;
		}
		// 防止配置时出现前后空格的情况
		// 非表达式的情况不处理掉空白字符，原始返回
		String trimedKey = key.trim();
		if (trimedKey.startsWith("${") && trimedKey.endsWith("}")) {
			String el = trimedKey.substring(2, trimedKey.length() - 1);
			String[] keypath = el.split("\\.");
			// 暂时仅支持"${TABLE_NAME.FIELD_NAME}"这一种模式
			if (keypath == null || keypath.length <= 1) {
				return key;
			}
			String tableName = keypath[0];

			if (!"system".equals(tableName)) {
				// 如果发现不是系统数据，则直接返回不处理
				return key;
			}

			String columnName = keypath[1];

			List<Map<String, String>> data = dataTable.get(tableName);
			if (data == null || data.isEmpty()) {
				return null;
			} else {
				// system数据源只会有一条记录
				Map<String, String> row = data.get(0);
				String columnValue = row.get(columnName);
				return columnValue;
			}
		} else {
			return key;
		}

	}

	public GetData getValue(int dataIndex, String key) {
		// log.entry("getValue()", dataIndex, key);
		if (key == null) {
			GetData gd = new GetData();
			gd.setValue(null);
			gd.setDataGetted(false);
			return gd;
		}
		if (dataIndex < 0) {
			GetData gd = new GetData();
			gd.setValue(null);
			gd.setDataGetted(false);
			return gd;
		}
		// 防止配置时出现前后空格的情况
		// 非表达式的情况不处理掉空白字符，原始返回
		String trimedKey = key.trim();
		if (trimedKey.startsWith("${") && trimedKey.endsWith("}")) {
			String el = trimedKey.substring(2, trimedKey.length() - 1);
			String[] keypath = el.split("\\.");
			// 暂时仅支持"${TABLE_NAME.FIELD_NAME}"这一种模式
			if (keypath == null || keypath.length <= 1) {
				GetData gd = new GetData();
				gd.setValue(key);
				gd.setDataGetted(false);
				return gd;
			}
			String tableName = keypath[0];

			// 当处理的内容不是指定数据源时，保留原数据标签
			if (currentTableName != null
					&& (!currentTableName.equals(tableName))) {
				GetData gd = new GetData();
				gd.setValue(key);
				gd.setDataGetted(false);
				return gd;
			}

			String columnName = keypath[1];
			List<Map<String, String>> data = dataTable.get(tableName);
			if (data == null || data.isEmpty()) {
				GetData gd = new GetData();
				gd.setValue(null);
				gd.setDataGetted(false);
				return gd;
			} else {
				if (dataIndex >= data.size()) {
					GetData gd = new GetData();
					gd.setValue(null);
					gd.setDataGetted(false);
					return gd;
				}
				Map<String, String> row = data.get(dataIndex);
				String columnValue = row.get(columnName);
				GetData gd = new GetData();
				gd.setValue(columnValue);
				gd.setDataGetted(true);
				return gd;
			}
		} else {
			GetData gd = new GetData();
			gd.setValue(key);
			gd.setDataGetted(false);
			return gd;
		}

	}

	public class GetData {
		private String value;
		private boolean isDataGetted = false;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isDataGetted() {
			return isDataGetted;
		}

		public void setDataGetted(boolean isDataGetted) {
			this.isDataGetted = isDataGetted;
		}

	}

	private int dataSize = -1;

	public int size() {
		return dataSize;
	}

	private String currentTableName = null;

	public void addValue(String tableName, Map<String, String> tableData) {
		List<Map<String, String>> data = dataTable.get(tableName);
		if (data == null) {
			data = new ArrayList<Map<String, String>>();
			dataTable.put(tableName, data);
		}

		data.add(tableData);
		dataSize = data.size();

		currentTableName = tableName;
	}

	public boolean isDataSourceCell(String key) {
		if (key == null) {
			return false;
		}
		key = key.trim();
		if (key.startsWith("${") && key.endsWith("}")) {
			String el = key.substring(2, key.length() - 1);
			String[] keypath = el.split("\\.");
			// 暂时仅支持"${TABLE_NAME.FIELD_NAME}"这一种模式
			if (keypath != null && keypath.length > 1) {

				if (!"system".equals(keypath[0])) {
					// 如果发现不是系统数据，则直接返回不处理
					return true;
				}
			}

		}
		return false;
	}
}
