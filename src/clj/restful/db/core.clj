(ns restful.db.core
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [mount.core :as mount]
            [restful.config :refer [env]])
  #_{:clj-kondo/ignore [:unused-import]}
  (:import org.bson.types.ObjectId))

(declare db*)

(declare db)

(def coll "users")

(mount/defstate db*
  :start
  (-> env
      :database-url
      (or "mongodb://127.0.0.1/restful")
      mg/connect-via-uri)
  :stop
  (when-some [conn (:conn db*)]
    (mg/disconnect conn)))

(mount/defstate db
  :start
  (:db db*))

(defn create-user [user]
  (mc/insert db coll user))

(defn update-user [id & {:as data}]
  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (mc/update db coll
             {:_id id}
             {$set data}))

(defn delete-user [id]
  (mc/remove db coll {:_id id}))

(defn get-user [id]
  (mc/find-one-as-map db coll {:_id (ObjectId. id)}))
