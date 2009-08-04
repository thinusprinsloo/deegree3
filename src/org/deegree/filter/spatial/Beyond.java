//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
   Department of Geography, University of Bonn
 and
   lat/lon GmbH

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package org.deegree.filter.spatial;

import org.deegree.commons.uom.Measure;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.MatchableObject;
import org.deegree.filter.expression.PropertyName;
import org.deegree.geometry.Geometry;

/**
 * TODO add documentation here
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author:$
 *
 * @version $Revision:$, $Date:$
 */
public class Beyond extends SpatialOperator {

    private final PropertyName param1;
    
    private final Geometry param2;
    
    private final Measure distance;
    
    public Beyond( PropertyName param1, Geometry param2, Measure distance ) {
        this.param1 = param1;
        this.param2 = param2;
        this.distance = distance;
    }

    public boolean evaluate( MatchableObject object )
                            throws FilterEvaluationException {
        for ( Object param1Value : param1.evaluate( object ) ) {
            Geometry geom = checkGeometryOrNull( param1Value );
            if ( param1Value != null && geom.isBeyond( param2, distance )) {
                return true;
            }
        }
        return false;
    }

    public String toString( String indent ) {
        // TODO Auto-generated method stub
        return null;
    }

}
