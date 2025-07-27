package com.example.capstoneone.Service;

import com.example.capstoneone.Model.CategoryId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryIdService {
    ArrayList<CategoryId> categoryIdList = new ArrayList<>();

    public ArrayList<CategoryId> getCategoryIdList() {
        return categoryIdList;
    }

    public void addCategoryId(CategoryId categoryId) {
        categoryIdList.add(categoryId);
    }

    public boolean updateCategoryId(String id , CategoryId categoryId){
        for (int i = 0; i < categoryIdList.size(); i++) {
            if(categoryIdList.get(i).getId().equals(id)){
                categoryIdList.set(i, categoryId);
                return true;
            }
        }
        return false; //wrong id
    }
    public boolean deleteCategoryId(String id){
        for (int i = 0; i < categoryIdList.size(); i++) {
            if(categoryIdList.get(i).getId().equals(id)){
                categoryIdList.remove(i);
                return true;
            }
        }
        return false; //wrong id
    }
    public CategoryId findById(String id) {
        for (CategoryId category : categoryIdList) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null; // لو ما لقاها
    }
}
