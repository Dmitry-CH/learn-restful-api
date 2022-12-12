(ns restful.config
  (:require [cprop.core :refer [load-config]]
            [mount.core :as mount]
            [taoensso.timbre :as log]))

(declare env)

(mount/defstate env
  :start
  (let [config (load-config)]
    (log/set-min-level! (or (-> config :logging :min-level)
                            :error))
    config))
