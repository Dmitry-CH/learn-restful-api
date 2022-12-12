(ns restful.api.v1.handler
  (:require [ring.util.http-response :as http-response]))

(defn health [_]
  (-> (http-response/ok "ok")
      (assoc-in [:headers "Cache-Control"] "no-cache")
      (assoc-in [:headers "Content-Type"] "text/html;charset=utf-8")))

(defn pong [_]
  (http-response/ok {:pong "pong"}))
