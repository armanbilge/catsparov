/*
 * Copyright 2022 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package catsparov

import cats.Applicative
import cats.data.Chain
import cats.kernel.Monoid

final case class Tests[F[_]](run: F[Chain[TestOutcome]])

object Tests {

  def monoid[F[_]](implicit F: Applicative[F]): Monoid[Tests[F]] =
    new Monoid[Tests[F]] {
      def empty: Tests[F] = Tests(F.pure(Chain.empty))
      def combine(x: Tests[F], y: Tests[F]): Tests[F] =
        Tests(F.map2(x.run, y.run)(_ ++ _))
    }

}

sealed abstract class TestOutcome
object TestOutcome {}
