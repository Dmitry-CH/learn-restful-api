(ns restful.api.v1.handler
  (:require [ring.util.http-response :as http-response]
            [taoensso.timbre :as log]
            [restful.db.core :as db]))

(defn health [_]
  (-> (http-response/ok "ok")
      (assoc-in [:headers "Cache-Control"] "no-cache")
      (assoc-in [:headers "Content-Type"] "text/html;charset=utf-8")))

(defn get-user-by-id [request]
  (let [id (-> request :path-params :id)]
    (try
      (-> id
          db/get-user
          http-response/ok)
      (catch Exception e
        (log/info (str "!!!!" e))
        (http-response/ok {:error "!!!"})))))
