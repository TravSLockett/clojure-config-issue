(defproject project "0.1.0-SNAPSHOT" 

  :repositories
  [["enonic" "https://repo.enonic.com/public/"]] ; For https://github.com/xapix-io/paos

  :dependencies
  [[org.clojure/clojure "1.10.0"]
   [org.clojure/core.async "0.4.490"]
   [org.clojure/core.match "0.3.0-alpha5"]
   [org.clojure/tools.cli "0.3.6"]
   [org.clojure/tools.namespace "0.3.0-alpha4"]
   [org.clojure/tools.nrepl "0.2.13"]
   [org.clojure/tools.logging "0.4.0"]
   [arctype/service "1.0.0"
    :exclusions [log4j]]
   [arctype/service.jose "0.1.0-SNAPSHOT"
    :exclusions [arctype/service]]
   [arctype/steelyx "0.4.0"
    :exclusions [org.slf4j/slf4j-nop org.slf4j/slf4j-api org.slf4j/slf4j-log4j12 org.onyxplatform/onyx]]
   [cheshire "5.8.0"]
   [http-kit "2.4.0-alpha3"]
   [io.sentry/sentry-log4j2 "1.7.5"
    :exclusions [org.apache.logging.log4j/log4j-api org.apache.logging.log4j/log4j-core]]
   [org.apache.logging.log4j/log4j-api "2.11.1"]
   [org.apache.logging.log4j/log4j-1.2-api "2.11.1"]
   [org.apache.logging.log4j/log4j-core "2.11.1"]
   [org.apache.logging.log4j/log4j-slf4j-impl "2.11.1"]
   [org.apache.logging.log4j/log4j-jcl "2.11.1"]
   [org.onyxplatform/onyx "0.14.6-SNAPSHOT"
    :exclusions [org.slf4j/slf4j-api org.slf4j/slf4j-nop log4j]]
   [org.onyxplatform/onyx-http "0.14.1.1-SNAPSHOT"
    :exclusions [onyxplatform/onyx]]
   [org.onyxplatform/onyx-amazon-sqs "0.14.1.0"
    :exclusions [onxyplatform/onyx]]
   [org.onyxplatform/onyx-amazon-kinesis "0.14.1.1-SNAPSHOT"
    :exclusions [onyxplatform/onyx]]
   [arctype/onyx-mongo "0.14.4.0-SNAPSHOT"
    :exclusions [onyxplatform/onyx]]
   [io.xapix/paos "0.2.4"]
   [prismatic/schema "1.1.12-SNAPSHOT"]
   [sundbry/resource "0.4.0"]]

  :plugins
  [[arctype/log4j2-plugins-cache "0.1.0"]]

  :middleware [leiningen.log4j2-plugins-cache/middleware]

  :source-paths ["src"]

  :test-selectors
  {:default #(not (:skip %))
   :unit #(and (not (:skip %)) (:unit %)) }

  :main ^:skip-aot project.data.main

  :jvm-opts ["-server" "-verbose:gc"]

  :repl-options
  {:timeout 300000}

  :profiles
  {:debug [:default]
   :release [:default :aot]

   :aot 
   {:aot :all
    :main project.data.main
    :omit-source true
    :javac-options ["-Dclojure.compiler.direct-linking=true"]
    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
