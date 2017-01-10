/* Copyright 2016 INSA Lyon
 * Inspired from "FirstOrderParser" by Dominic Scheurer.
 *
 * HardenedGolo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FirstOrderParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FirstOrderParser.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.eclipse.golo.compiler.jgoloparser.binary;

import org.eclipse.golo.compiler.jgoloparser.JGFormula;
import org.eclipse.golo.compiler.jgoloparser.JGTerm;
import org.eclipse.golo.compiler.jgoloparser.visitor.SpecTreeVisitor;

import java.util.HashSet;
import java.util.Set;

public abstract class JGBinary implements JGFormula {

  private JGFormula left;

  private JGFormula right;

  private Operator operator;

  JGBinary(JGFormula left, Operator operator, JGFormula right) {
    this.left = left;
    this.right = right;
    this.operator = operator;
  }

  public JGFormula getLeft() {
    return left;
  }

  public JGFormula getRight() {
    return right;
  }

  public String getOperator() {
    return operator.toString();
  }

  @Override
  public String toString() {
    return "(" + left + " " + operator + " " + right + ")";
  }

  enum Operator {
    PLUS("+"),
    MINUS("-"),
    LESS("<"),
    DIVIDE("/"),
    EQUALS("="),
    MODULO("%"),
    GREATER(">"),
    NOT_EQUALS("<>"),
    IMPLICATIVE("->"),
    CONJUNCTIVE("/\\"),
    DISJUNCTIVE("\\/"),
    MULTIPLICATION("*"),
    LESS_OR_EQUALS("<="),
    GREATER_OR_EQUALS(">=");

    private final String symbol;

    Operator(String symbol) {
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return symbol;
    }

    static Operator parse(String operator) {
      for (Operator current : values()) {
        if (current.symbol.equals(operator)) {
          return current;
        }
      }
      throw new RuntimeException("Found unknown binary operator: " + operator);
    }
  }
}
