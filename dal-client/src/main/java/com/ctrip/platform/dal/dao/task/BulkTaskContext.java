package com.ctrip.platform.dal.dao.task;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ctrip.platform.dal.dao.UpdatableEntity;

/**
 * An execution context that holds only intermediate product during bulk task execution.
 * The attributes here are for readonly purpose.
 * The reason why DalHints is not used for execution context is because the hints is 
 * a container for all user defined context. So we need a stand-alone class to hold 
 * what dal internal created for intermediate use.   
 * @author jhhe
 */
public class BulkTaskContext<T> {
	private List<T> rawPojos;
	
	// This is only for batch and combined insert operation
	private Set<String> unqualifiedColumns;
	
	private boolean isUpdatableEntity;
	// This is only for batch update operation
	private Map<String, Boolean> pojoFieldStatus;
	
	public Map<String, Boolean> getPojoFieldStatus() {
		return pojoFieldStatus;
	}

	public void setPojoFieldStatus(Map<String, Boolean> pojoFieldStatus) {
		this.pojoFieldStatus = pojoFieldStatus;
	}

	public BulkTaskContext(List<T> rawPojos) {
		this.rawPojos = rawPojos;
		if(rawPojos != null && rawPojos.size() > 0)
			isUpdatableEntity = rawPojos.get(0) instanceof UpdatableEntity;
	}

	public boolean isUpdatableEntity() {
		return isUpdatableEntity;
	}

	public List<T> getRawPojos() {
		return rawPojos;
	}

	public Set<String> getUnqualifiedColumns() {
		return unqualifiedColumns;
	}

	public void setUnqualifiedColumns(Set<String> unqualifiedColumns) {
		this.unqualifiedColumns = unqualifiedColumns;
	}
}
