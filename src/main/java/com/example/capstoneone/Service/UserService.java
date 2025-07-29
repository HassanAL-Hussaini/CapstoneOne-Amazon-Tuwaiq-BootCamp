package com.example.capstoneone.Service;

import com.example.capstoneone.Model.Product;
import com.example.capstoneone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.PreFlightRequestHandler;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ProductService productService;
    private final ArrayList<User> users = new ArrayList<>();
    private final PreFlightRequestHandler preFlightRequestHandler;

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(String id, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.remove(users.get(i));
                return true;
            }
        }
        return false;
    }
 //Extra Endpoint
    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    //تفضيلات المهتمين
    public ArrayList<Product> preferenceInterester(String userId){
        User user = getUserById(userId);
        ArrayList<Product> products = productService.getAllProducts();
        ArrayList<Product> preferenceProducts = new ArrayList<>();
        if(user != null){
            //هذي الصفحه فقط للي الرول حقهم interester
            if(user.getRole().equals("Interester")){
                for (Product product : products){
                    if(user.getBalance() >= product.getPrice() &&
                       product.getCategoryId().getName().equals(user.getPreference())){
                        preferenceProducts.add(product);
                    }
                }
            }
        }
        return preferenceProducts;
    }

    public ArrayList<User> getFriends(String userId){
        User user = getUserById(userId);
        ArrayList<User> friends = new ArrayList<>();
        if(user == null){
            return null;
        }
            for(User u : users){
                if(!u.getId().equals(userId) &&  // تأكد أنك ما تضيف نفسك كصديق
                        u.getPreference().equals(user.getPreference()) && //متشاركين في preference
                        u.getAccount().equals("public")){// account must be public
                    friends.add(u);
                }
            }
        return getClosest3FriendsUsingSort(friends , user.getBalance());
    }

    public ArrayList<User> getClosest3FriendsUsingSort(ArrayList<User> friends, double userBalance) {
        // ننسخ القائمة علشان ما نعدل الأصلية
        ArrayList<User> sortedFriends = new ArrayList<>(friends);

        // نرتبهم بناءً على الفرق في الرصيد (من الأصغر إلى الأكبر)
        sortedFriends.sort((u1, u2) -> {
            double diff1 = Math.abs(u1.getBalance() - userBalance);//abs = abslute
            double diff2 = Math.abs(u2.getBalance() - userBalance);
            return Double.compare(diff1, diff2);
        });

        // نرجع فقط أول 3 (أو أقل إذا ما فيه غيرهم)
        ArrayList<User> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, sortedFriends.size()); i++) {
            top3.add(sortedFriends.get(i));
        }

        return top3;
    }

}
