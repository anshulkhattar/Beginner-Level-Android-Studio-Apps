# Bandung Destination

**Standar Penulisan Code**

# Varibale JAVA
```
- Button          : Button btnCart;
- TextView        : TextView tvCart;
- ImageView       : ImageView imgCart;
- View            : View viewCart;
- RecyclerView    : RecyclerView recyclerView;
- Adapter         : Adapter adapter;
```

# Atribut ID XML
```
- Button          : android:id="@+id/btn_cart"
- TextView        : android:id="@+id/txt_cart"
- ImageView       : android:id="@+id/img_cart"
- View            : android:id="@+id/view_cart"
- RecyclerView    : android:id="@+id/recycler_view"
```

# Implementasi

Gunakan extends **BaseActivity** di setiap Actiity yang di buat

```
public class MainActivity extends BaseActivity {
    Toolbar toolbar;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
        initListeners();
    }
    public void findViews() { 
    }

    @Override
    public void initViews() { 
    }

    @Override
    public void initListeners() {
    }
}
```

**Semua kode java yang berkaitan dengan inisialisasi ID XML gunakan method dibawah :** 
```
public void findViews()
```

contoh :
```
public void findViews(){
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    button = (Button) findViewById(R.id.button);
}
```

**Semua kode java yang berkaitan dengan Assigment, dan Inisialisasi Object, Adapter, dll gunakan method dibawah :** 
```
public void initViews()
```

contoh :
```
public void initViews(){
    adapter = new RequestAdapter(this);
    listAdapter.setAdapter(adapter);
}
```

**Semua kode java yang berkaitan dengan Listenner, Aksi ketika di Klik, dll gunakan method dibawah :** 
```
public void initListeners()
```

contoh :
```
public void initListeners(){
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        
        }
    });
}
```
