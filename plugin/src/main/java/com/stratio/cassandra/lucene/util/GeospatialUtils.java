/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.stratio.cassandra.lucene.util;

import com.spatial4j.core.context.jts.JtsSpatialContext;
import com.spatial4j.core.shape.jts.JtsGeometry;
import com.stratio.cassandra.lucene.IndexException;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.commons.lang3.StringUtils;

/**
 * Utilities for Java Topology Suite (JTS) related stuff.
 *
 * This class depends on <a href="http://www.vividsolutions.com/jts">Java Topology Suite (JTS)</a>. This library can't
 * be distributed together with this project due to license compatibility problems, but you can add it by putting <a
 * href="http://search.maven.org/remotecontent?filepath=com/vividsolutions/jts-core/1.14.0/jts-core-1.14.0.jar">jts-core-1.14.0.jar</a>
 * into Cassandra lib directory.
 *
 * @author Andres de la Pena {@literal <adelapena@stratio.com>}
 */
public class GeospatialUtils {

    /**
     * Returns the {@link JtsGeometry} represented by the specified WKT text.
     *
     * @param context the JTS spatial context
     * @param string the WKT text
     * @return the parsed geometry
     */
    public static JtsGeometry geometryFromWKT(JtsSpatialContext context, String string) {
        if (StringUtils.isBlank(string)) {
            throw new IndexException("Shape shouldn't be blank");
        }
        try {
            GeometryFactory geometryFactory = context.getGeometryFactory();
            WKTReader reader = new WKTReader(geometryFactory);
            Geometry geometry = reader.read(string);
            return context.makeShape(geometry);
        } catch (ParseException e) {
            throw new IndexException(e, "Shape '%s' is not parseable", string);
        }
    }
}
