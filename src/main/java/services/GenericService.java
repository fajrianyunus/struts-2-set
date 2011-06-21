package services;

import java.util.List;

public interface GenericService<entityClass, idClass> {
	public void deleteAll();
	public List<entityClass> findAll();
	public void save(entityClass entity);
	public void removeById(idClass id);
	public entityClass find(idClass id);
	public Long countAll();
}
