package hw05

import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.numerics.abs
import breeze.optimize.{DiffFunction, LBFGS}
import breeze.stats.mean

import scala.annotation.tailrec


object Main extends App {

  val model = new SGD()

  val X_train: DenseMatrix[Double] = DenseMatrix.rand[Double](1000000, 3)
  val y_train: DenseVector[Double] = X_train * DenseVector[Double](1.5, 0.3, -0.7)

  val X_test: DenseMatrix[Double] = DenseMatrix.rand[Double](1000000, 3)
  val y_test: DenseVector[Double] = X_train * DenseVector[Double](1.5, 0.3, -0.7)

  model.fit(X_train, y_train)
  val y_hat: DenseVector[Double] = model.predict(X_test)
  
  println(model.cofficients())
}


class SGD() {
  var w: DenseVector[Double] = _

  def fit(X: DenseMatrix[Double], y: DenseVector[Double]): Unit = {
    val ones = DenseMatrix.fill[Double](X.rows, 1)(1)
    val X_ = DenseMatrix.horzcat(ones, X)
    w = DenseVector.rand[Double](X_.cols)

    val optimizationPipeline = new DiffFunction[DenseVector[Double]] {
      def calculate(w: DenseVector[Double]): (Double, DenseVector[Double]) = {
        val e = X_ * w - y
        val loss = sum(e ^:^ 2.0) / (2 * X_.rows)
        val grad = e.t * X_ /:/ (2.0 * X_.rows)
        (loss, grad.t)
      }
    }
    val optimizer = new LBFGS[DenseVector[Double]]()
    w = optimizer.minimize(optimizationPipeline, DenseVector(0.0, 0.0, 0.0, 0.0))
  }

  def predict(X: DenseMatrix[Double]): DenseVector[Double] = {
    val ones = DenseMatrix.fill[Double](X.rows, 1)(1)
    val X_ = DenseMatrix.horzcat(ones, X)
    X_ * w
  }

  def cofficients(): DenseVector[Double] = {
    w(1 to 3)
  }
}

