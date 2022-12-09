(ns restful.handler)

(defn handler []
  {:status 200, :body "ok"})

(defn app []
  (handler))
