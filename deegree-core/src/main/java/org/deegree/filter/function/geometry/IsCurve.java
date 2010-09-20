//$HeadURL$
package org.deegree.filter.function.geometry;

import java.util.List;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.property.Property;
import org.deegree.filter.Expression;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.MatchableObject;
import org.deegree.filter.custom.FunctionProvider;
import org.deegree.filter.expression.Function;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.multi.MultiCurve;
import org.deegree.geometry.multi.MultiLineString;
import org.deegree.geometry.primitive.Curve;

/**
 * <code>IsCurve</code>
 * 
 * @author <a href="mailto:schmitz@lat-lon.de">Andreas Schmitz</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class IsCurve extends Function implements FunctionProvider {

    /****/
    public IsCurve() {
        // needed for SPI
        super( "IsCurve", null );
    }

    /**
     * @param exprs
     */
    public IsCurve( List<Expression> exprs ) {
        super( "IsCurve", exprs );
        if ( exprs.size() != 1 ) {
            throw new IllegalArgumentException( "IsCurve requires exactly one parameter." );
        }
    }

    @Override
    public IsCurve create( List<Expression> params ) {
        return new IsCurve( params );
    }

    @Override
    public TypedObjectNode[] evaluate( MatchableObject f )
                            throws FilterEvaluationException {

        TypedObjectNode[] vals = getParams()[0].evaluate( f );
        if ( vals.length != 1 || !( vals[0] instanceof Geometry ) && !( vals[0] instanceof Property )
             && !( ( (Property) vals[0] ).getValue() instanceof Geometry ) ) {
            return new TypedObjectNode[0];
            // throw new FilterEvaluationException( "The argument to the Is*** functions must be a geometry." );
        }
        Geometry geom = vals[0] instanceof Geometry ? (Geometry) vals[0] : (Geometry) ( (Property) vals[0] ).getValue();

        // TODO is handling of multi geometries like this ok?
        boolean result = geom instanceof Curve || geom instanceof MultiCurve || geom instanceof MultiLineString;
        return new TypedObjectNode[] { new PrimitiveValue( Boolean.valueOf( result ) ) };
    }
}