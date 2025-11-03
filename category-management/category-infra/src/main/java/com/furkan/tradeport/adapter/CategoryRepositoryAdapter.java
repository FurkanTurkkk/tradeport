package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaCategoryEntity;
import com.furkan.tradeport.exception.CategoryNotFoundException;
import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.persistence.SpringDataCategoryRepository;
import com.furkan.tradeport.port.CategoryRepositoryPort;
import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.CategoryName;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final SpringDataCategoryRepository repository;

    public CategoryRepositoryAdapter(SpringDataCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return repository.findById(id.getValue())
                .map(this::toDomain);
    }

    @Override
    public Optional<Category> findByName(CategoryName name) {
        return repository.findByName(name.getValue())
                .map(this::toDomain);
    }

    @Override
    public List<Category> findAll() {
        List<JpaCategoryEntity> entities = repository.findAll();
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public void save(Category category) {
        repository.save(new JpaCategoryEntity(category.getId().getValue(),
                category.getName().getValue(),
                category.getParentId().getValue(),
                category.getCreatedAt(),
                category.getUpdatedAt()));
    }

    @Override
    public void deleteById(CategoryId id) {
        repository.deleteById(id.getValue());
    }

    @Override
    public Category update(Category category) {
        Optional<JpaCategoryEntity> entity = repository.findById(category.getId().getValue());
        if(entity.isEmpty()){
            throw new CategoryNotFoundException("Category could not found");
        }
        entity.get().setName(category.getName().getValue());
        entity.get().setParentId(category.getParentId().getValue());
        entity.get().setUpdatedAt(Instant.now());
        repository.save(entity.get());
        return toDomain(entity.get());
    }

    private Category toDomain(JpaCategoryEntity categoryEntity) {
        return new Category(CategoryId.of(categoryEntity.getId()),
                CategoryName.of(categoryEntity.getName()),
                CategoryId.of(categoryEntity.getParentId()));
    }
}
