(ns clojurewerkz.welle.test.conversion-test
  (:use     clojure.test clojurewerkz.welle.conversion)
  (:import [com.basho.riak.client.cap Quora Quorum]))

(deftest test-quorum-conversion
  (testing "int-to-quorum conversion"
    (are [i q] (is (= (to-quorum i) q))
      1 (Quorum. 1)
      2 (Quorum. 2)
      3 (Quorum. 3)
      4 (Quorum. 4)
      5 (Quorum. 5))
    (are [i q] (is (not (= (to-quorum i) q)))
      1 (Quorum. 5)
      2 (Quorum. 4)
      3 (Quorum. 2)
      4 (Quorum. 1)
      5 (Quorum. 3)))
  (testing "quora-to-quorum conversion"
    (are [qa] (is (= (to-quorum qa) (Quorum. ^Quora qa)))
      Quora/ONE
      Quora/QUORUM
      Quora/ALL
      Quora/DEFAULT))
  (testing "quorum-to-quorum conversion"
    (are [qm] (is (= (to-quorum qm) qm))
      (Quorum. Quora/ONE)
      (Quorum. Quora/QUORUM)
      (Quorum. Quora/ALL)
      (Quorum. Quora/DEFAULT)
      (Quorum. 1)
      (Quorum. 2)
      (Quorum. 3)
      (Quorum. 10)
      (Quorum. 50)
      (Quorum. 100))))
