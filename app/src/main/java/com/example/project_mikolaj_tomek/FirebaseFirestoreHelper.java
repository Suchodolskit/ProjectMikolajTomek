package com.example.project_mikolaj_tomek;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.project_mikolaj_tomek.Models.FirestoreObject;
import com.example.project_mikolaj_tomek.Models.Product;
import com.example.project_mikolaj_tomek.Models.Recipe;
import com.example.project_mikolaj_tomek.Models.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FirebaseFirestoreHelper {

    private String TAG = "FirebaseFirestoreHelper";

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseFirestore store;
    private Context context;

    public FirebaseFirestoreHelper(Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        store = FirebaseFirestore.getInstance();
        this.context = context;
    }

    public void SaveData(String collection, FirestoreObject object) {
        DocumentReference ref = store.collection(collection).document(object.getId());
        ref.set(object);
    }

    public List<Object> GetCollection(String collection) {
        final ArrayList<Object> Objects = new ArrayList<>();
        store.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Objects.add(document);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return Objects;
    }

    public void GetRecipes(final RecipeAdapter adapter) {
        final LinkedList<Recipe> list = new LinkedList<>();
        store.collection("recipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Recipe recipe = new Recipe(
                                        document.getString("id"),
                                        document.getString("title"),
                                        document.getDate("creationDate"),
                                        document.getLong("preparationTime"),
                                        document.getString("summary"),
                                        document.getString("description"),
                                        (List<Product>)document.get("products"),
                                        null,
                                        document.getString("author")
                                );
                                FirebaseStorageHelper helper = new FirebaseStorageHelper();
                                helper.SetImage(recipe,context);
                                list.add(recipe);
                            }
                            adapter.setList(list);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void GetProducts(final ProductListAdapter adapter) {
        final LinkedList<Product> list = new LinkedList<>();
        store.collection("FoodProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                    Product product = new Product(
                                            document.getString("id"),
                                            document.getString("name"),
                                            null
                                    );
                                list.add(product);
                            }
                            adapter.SetList(list);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void RecipesWithProducts(final RecipeAdapter adapter, List<Product> products) {
            final LinkedList<Recipe> list = new LinkedList<>();
            CollectionReference collectionReference = store.collection("recipes");
            Query searchRecipesWithProducts = collectionReference.whereEqualTo("products",products);
            searchRecipesWithProducts.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Recipe recipe = new Recipe(
                                    document.getString("id"),
                                    document.getString("title"),
                                    document.getDate("creationDate"),
                                    document.getLong("preparationTime"),
                                    document.getString("summary"),
                                    document.getString("description"),
                                    (List<Product>)document.get("products"),
                                    null,
                                    document.getString("author")
                            );
                            FirebaseStorageHelper helper = new FirebaseStorageHelper();
                            helper.SetImage(recipe,context);
                            list.add(recipe);
                        }
                        adapter.setList(list);
                    }
                }
            });
    }

    public void  AddUser(UserObject user) {
        SaveData("Users",user);
    }

    public void FoodProductsInitialise() {
        ArrayList<String> productNames = new ArrayList<>();
        productNames.add("bacon");       productNames.add("plum");       productNames.add("cabbage");
        productNames.add("salami");      productNames.add("pomegranate");productNames.add("carrot");
        productNames.add("sausage");     productNames.add("raspberry");  productNames.add("cauliflower");
        productNames.add("apple");       productNames.add("onion");      productNames.add("celery");
        productNames.add("apricot");     productNames.add("redcurrant"); productNames.add("chili pepper");
        productNames.add("banana");      productNames.add("rhubarb");    productNames.add("courgette");
        productNames.add("blackberry");  productNames.add("strawberry"); productNames.add("cucumber");
        productNames.add("blackcurrant");productNames.add("anchovy");    productNames.add("garlic");
        productNames.add("blueberry");   productNames.add("cod");        productNames.add("ginger");
        productNames.add("cherry");      productNames.add("herring");    productNames.add("leek");
        productNames.add("coconut");     productNames.add("mackerel");   productNames.add("lettuce");
        productNames.add("fig");         productNames.add("");           productNames.add("onion");
        productNames.add("gooseberry");  productNames.add("pilchard");   productNames.add("peas");
        productNames.add("grape");       productNames.add("salmon");     productNames.add("pepper");
        productNames.add("grapefruit");  productNames.add("squash");     productNames.add("potato");
        productNames.add("kiwi");        productNames.add("sardine");    productNames.add("pumpkin");
        productNames.add("lemon");       productNames.add("trout");      productNames.add("radish");
        productNames.add("lime");        productNames.add("tuna");       productNames.add("swede");
        productNames.add("mango");       productNames.add("asparagus");  productNames.add("sweet potato");
        productNames.add("melon");       productNames.add("aubergine");  productNames.add("sweetcorn");
        productNames.add("orange");      productNames.add("avocado");    productNames.add("tomato");
        productNames.add("peach");       productNames.add("beetroot");   productNames.add("turnip");
        productNames.add("pear");        productNames.add("broad beans");productNames.add("spinach");
        productNames.add("pineapple");   productNames.add("broccoli");   productNames.add("spring onion");

//        for(int i=0;i<productNames.size();i++)
//        {
//            Product p = new Product(String.valueOf(i),productNames.get(i),null);
//            SaveData("FoodProducts",p);
//        }

        CollectionReference cr = store.collection("FoodProducts");
        Query test = cr.whereGreaterThanOrEqualTo("name","peach");
        test.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        Map<String,Object> map = document.getData();
                        Product p = new Product(map.get("id").toString(),map.get("name").toString(),null);
                        String id = p.getId();
                    }
                }
            }
        });

    }
}
