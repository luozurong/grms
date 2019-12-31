package com.jlit.db.extend;

/**
 * 装载Entity信息的内部类.
 *
 */
class EntityInfo {
    /**
     * entity是否undeleteable的标志
     */
    boolean isUndeletable = false;

    /**
     * 标识状态的属性名
     */
    String statusProperty;

	public EntityInfo(Class entityClass) {
		init(entityClass);
	}

	/**
	 * 初始函数,判断EntityClass是否UndeletableEntity.
	 */
	@SuppressWarnings("unchecked")
	private void init(Class entityClass) {
		// 通过EntityClass的interface判断entity是否undeletable
		if (UndeletableEntity.class.isAssignableFrom(entityClass)) {
			isUndeletable = true;
			statusProperty = UndeleteableEntityOperation.STATUS;
		}

		// 通过EntityClass的annotation判断entity是否undeletable
		if (entityClass.isAnnotationPresent(Undeletable.class)) {
			isUndeletable = true;
			Undeletable anno = (Undeletable) entityClass.getAnnotation(Undeletable.class);
			statusProperty = anno.status();
		}
	}
}
