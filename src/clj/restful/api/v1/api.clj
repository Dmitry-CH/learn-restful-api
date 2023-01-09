(ns restful.api.v1.api
  (:require [restful.api.v1.handler :as handler]))

(defn routes []
  ["/api/v1"
   ["/health"
    {:name ::health
     :get handler/health}]
   ["/users"
    {:name ::users
     :get handler/get-users}]
   ["/users/:id"
    {:name ::users-id
     :get handler/get-user-by-id}]])
