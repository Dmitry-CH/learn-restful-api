(ns restful.handler
  (:require [mount.core :as mount]
            [muuntaja.core :as m]
            #_[reitit.coercion.spec :as rcs]
            [reitit.ring :as ring]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as rrmm] 
            [ring.util.http-response :as http-response] 
            [restful.api.v1.api :refer [routes]]))

(declare app-router)

(mount/defstate app-router
  :start
  (ring/ring-handler
    (ring/router
      (routes)
      {:data {:muuntaja m/instance
              #_#_:coercion rcs/coercion
              :middleware [rrmm/format-middleware
                           #_rrc/coerce-exceptions-middleware
                           #_rrc/coerce-request-middleware
                           rrc/coerce-response-middleware]}})
    (ring/create-default-handler
      {:not-found (constantly (http-response/not-found))})))

(defn app []
  #'app-router)
