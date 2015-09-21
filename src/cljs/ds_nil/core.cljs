(ns ds-nil.core
  (:require [datascript :as d]
            [cognitect.transit :as transit]))

(enable-console-print!)
(def schema {:user/id          {:db/unique :db.unique/identity}})

(defonce conn (d/create-conn schema))

(defn read [data]
  (transit/read (transit/reader :json) data))


(let [data (read (.-innerHTML (.getElementById js/document "data")))]
  (d/transact! conn (:users data)))

(print (:user/name (d/entity @conn [:user/id nil])))