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
[ログイン画面](http://localhost:8080/admin/loginemployee)|LoginServlet|[login](resources/templates/login.html)
[メニュー画面](http://localhost:8080/admin/menu)|MenuServlet|[menu](resources/templates/menu.html)
[アカウント登録(入力)画面](http://localhost:8080/admin/registeraccount/input)|RegisterAccountController|[input](resources/templates/registeraccount/input.html)
[アカウント登録(確認)画面](http://localhost:8080/admin/registeraccount/confirm)|RegisterAccountController|[confirm](resources/templates/registeraccount/confirm.html)
[アカウント登録(完了)画面](http://localhost:8080/admin/registeraccount/complete)|RegisterAccountController|[complete](resources/templates/registeraccount/complete.html)
[商品検索画面](http://localhost:8080/admin/productlist)|SearchProductController|[Post](resources/templates/product/productlist.html)
[商品削除(確認)画面](http://localhost:8080/admin/deleteproduct/confirm)|DeleteProductController|[confirm](resources/templates/deleteproduct/confirm.html)
[商品削除(完了)画面](http://localhost:8080/admin/deleteproduct/complete)|DeleteProductController|[complete](resources/templates/deleteproduct/complete.html)
[商品修正(入力)画面](http://localhost:8080/admin/updateproduct/input)|UpdateProductController|[input](resources/templates/updateproduct/input.html)
[商品修正(確認)画面](http://localhost:8080/admin/updateproduct/confirm)|UpdateProductController|[confirm](resources/templates/updateproduct/confirm.html)
[商品修正(完了)画面](http://localhost:8080/admin/updateproduct/complete)|UpdateProductController|[complete](resources/templates/updateproduct/complete.html)
[商品登録(入力)画面](http://localhost:8080/admin/registerproduct/input)|RegisterProductController|[input](resources/templates/registerproduct/input.html)
[商品登録(確認)画面](http://localhost:8080/admin/registerproduct/confirm)|RegisterProductController|[confirm](resources/templates/registerproduct/confirm.html)
[商品登録(完了)画面](http://localhost:8080/admin/registerproduct/complete)|RegisterProductController|[complete](resources/templates/registerproduct/complete.html)
[商品カテゴリ登録(入力)画面](http://localhost:8080/admin/registerproductcategory/input)|RegisterProductCategoryController|[input](resources/templates/registerproductcategory/input.html)
[商品カテゴリ登録(確認)画面](http://localhost:8080/admin/registerproductcategory/confirm)|RegisterProductCategoryController|[confirm](resources/templates/registerproductcategory/confirm.html)
[商品カテゴリ登録(完了)画面](http://localhost:8080/admin/registerproductcategory/complete)|RegisterProductCategoryController|[complete](resources/templates/registerproductcategory/complete.html)


フロントエンド
[トップ画面](http://localhost:8080/top)|Controller|[top](resources/templates/front/top.html)
[顧客ログイン画面](http://localhost:8080/login)|Controller|[login](resources/templates/front/login.html)
[顧客アカウント登録(入力)画面](http://localhost:8080/registeraccount/input)|Controller|[input](resources/templates/front/resisteraccount/input.html)
[顧客アカウント登録(確認)画面](http://localhost:8080/registeraccount/confirm)|Controller|[confirm](resources/templates/front/resisteraccount/confirm.html)
[顧客アカウント登録(完了)画面](http://localhost:8080/registeraccount/complete)|Controller|[complete](resources/templates/front/resisteraccount/conplete.html)
[カテゴリ商品検索画面](http://localhost:8080/productlist)|Controller|[Post](resources/templates/front/product/productlist.html)
[商品詳細画面](http://localhost:8080/productDetails)|Controller|[Post](resources/templates/front/product/productdetails.html)
[購入(入力)画面](http://localhost:8080/purchase/input)|Controller|[input](resources/templates/front/purchase/input.html)
[購入(確認)画面](http://localhost:8080/purchase/confirm)|Controller|[confirm](resources/templates/front/purchase/confirm.html)
[購入(完了)画面](http://localhost:8080/purchase/complete)|Controller|[complete](resources/templates/front/purchase/conplete.html)
[購入履歴一覧画面](http://localhost:8080/purchaseHistory)|Controller|[Post](resources/templates/purchase/purchasehistory.html)
[購入履歴詳細画面](http://localhost:8080/purchaseHistorydetails)|Controller|[Post](resources/templates/purchase/purchaseHistorydetails.html)


## コーディング規約

### 基本規約

本システムのコーディング規約は基本的には[オブジェクト倶楽部版 Java コーディング規約](http://objectclub.jp/community/codingstandard/CodingStd.pdf)に準ずる

