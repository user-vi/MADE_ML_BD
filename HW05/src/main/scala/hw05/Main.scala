package hw05

import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.numerics.abs
import breeze.stats.mean

import scala.annotation.tailrec


object Main extends App {

  def mape(y: DenseVector[Double], y_hat: DenseVector[Double]): Double = {
    mean(abs((y - y_hat) / y))
  }

  val model = new SGD(epochs = 1000, learningRate = 0.001)

  val X_train: DenseMatrix[Double] = DenseMatrix.rand[Double](1000000, 3)
  val y_train: DenseVector[Double] = X_train * DenseVector[Double](1.5, 0.3, -0.7)

  val X_test: DenseMatrix[Double] = DenseMatrix.rand[Double](1000000, 3)
  val y_test: DenseVector[Double] = X_train * DenseVector[Double](1.5, 0.3, -0.7)

  model.fit(X_train, y_train)
  val y_hat: DenseVector[Double] = model.predict(X_test)

  val score = mape(y_test, y_hat)
  println("mape = " + score)
}


class SGD(val epochs: Int, val learningRate: Double) {
  var w: DenseVector[Double] = _

  def fit(X: DenseMatrix[Double], y: DenseVector[Double]): Unit = {
    val ones = DenseMatrix.fill[Double](X.rows, 1)(1)
    val X_ = DenseMatrix.horzcat(ones, X)
    w = DenseVector.rand[Double](X_.cols)

    def updateWeight(row: Int, w: DenseVector[Double]): DenseVector[Double] = {
      val grad = X_(row, ::) * (X_(row, ::) * w - y(row))
      w - this.learningRate * grad.t
    }

    @tailrec
    def loop(increment: Int,
             f: (Int, DenseVector[Double]) => DenseVector[Double],
             w: DenseVector[Double],
            ): DenseVector[Double] = {
      if (increment == 1) w
      else loop(increment - 1, updateWeight, updateWeight(increment - 1, w))
    }

    @tailrec
    def train(epoch: Int): DenseVector[Double] = {
      if (epoch == 1) loop(increment = X_.rows, updateWeight, w)
      else train(epoch - 1)
    }

    train(this.epochs)
  }

  def predict(X: DenseMatrix[Double]): DenseVector[Double] = {
    val ones = DenseMatrix.fill[Double](X.rows, 1)(1)
    val X_ = DenseMatrix.horzcat(ones, X)
    X_ * w
  }
}

