# Fullness Stationary社 文具/雑貨販売システム

##　チーム名
#### S-starter

## チームメンバー

- 高良　星海さん（チームリーダー）
- 舘川　佳苗さん（テクニカルリーダー）
- 山崎　大夢さん
- 古市　遼平さん

## チーム目標

- すべての実装を完成させる
- 全員がすべての実装を理解し、個人の技術向上に努める

## チームのルール
- わからないことがあれば①自分で調べる②チームで解決する③上司に聞くを徹底する
- 午前中2回、午後2回進捗確認を行う
- 質問はGitHub Issueにあげ、誰が見てもわかるよう丁寧に書く

## URL一覧

画面名|サーブレット名|メソッド名
---|---|---
[ログイン画面](http://localhost:8080/loginemployee)|LoginServlet|[login](resources/templates/login.html)
[メニュー画面](http://localhost:8080/menu)|MenuServlet|[menu](resources/templates/menu.html)
[アカウント登録(入力)画面](http://localhost:8080/registeraccount/input)|RegisterAccountController|[input](resources/templates/registeraccount/input.html)
[アカウント登録(確認)画面](http://localhost:8080/registeraccount/confirm)|RegisterAccountController|[confirm](resources/templates/registeraccount/confirm.html)
[アカウント登録(完了)画面](http://localhost:8080/registeraccount/complete)|RegisterAccountController|[complete](resources/templates/registeraccount/complete.html)
[商品検索画面](http://localhost:8080/product/productlist)|SearchProductController|[Post](resources/templates/product/productlist.html)
[商品削除(確認)画面](http://localhost:8080/deleteproduct/confirm)|DeleteProductController|[confirm](resources/templates/deleteproduct/confirm.html)
[商品削除(完了)画面](http://localhost:8080/deleteproduct/complete)|DeleteProductController|[complete](resources/templates/deleteproduct/complete.html)
[商品修正(入力)画面](http://localhost:8080/updateproduct/input)|UpdateProductController|[input](resources/templates/updateproduct/input.html)
[商品修正(確認)画面](http://localhost:8080/updateproduct/confirm)|UpdateProductController|[confirm](resources/templates/updateproduct/confirm.html)
[商品修正(完了)画面](http://localhost:8080/updateproduct/complete)|UpdateProductController|[complete](resources/templates/updateproduct/complete.html)
[商品登録(入力)画面](http://localhost:8080/registerproduct/input)|RegisterProductController|[input](resources/templates/registerproduct/input.html)
[商品登録(確認)画面](http://localhost:8080/registerproduct/confirm)|RegisterProductController|[confirm](resources/templates/registerproduct/confirm.html)
[商品登録(完了)画面](http://localhost:8080/registerproduct/complete)|RegisterProductController|[complete](resources/templates/registerproduct/complete.html)
[商品カテゴリ登録(入力)画面](http://localhost:8080/registerproductcategory/input)|RegisterProductCategoryController|[input](resources/templates/registerproductcategory/input.html)
[商品カテゴリ登録(確認)画面](http://localhost:8080/registerproductcategory/confirm)|RegisterProductCategoryController|[confirm](resources/templates/registerproductcategory/confirm.html)
[商品カテゴリ登録(完了)画面](http://localhost:8080/registerproductcategory/complete)|RegisterProductCategoryController|[complete](resources/templates/registerproductcategory/complete.html)



## コーディング規約

### 基本規約

本システムのコーディング規約は基本的には[オブジェクト倶楽部版 Java コーディング規約](http://objectclub.jp/community/codingstandard/CodingStd.pdf)に準ずる

