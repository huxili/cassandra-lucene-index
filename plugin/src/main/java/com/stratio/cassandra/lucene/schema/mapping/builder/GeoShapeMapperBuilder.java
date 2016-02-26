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

package com.stratio.cassandra.lucene.schema.mapping.builder;

import com.stratio.cassandra.lucene.schema.mapping.GeoPointMapper;
import com.stratio.cassandra.lucene.schema.mapping.GeoShapeMapper;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * {@link MapperBuilder} to build a new {@link GeoPointMapper}.
 *
 * @author Andres de la Pena {@literal <adelapena@stratio.com>}
 */
public class GeoShapeMapperBuilder extends MapperBuilder<GeoShapeMapper, GeoShapeMapperBuilder> {

    /** The name of the column to be mapped. */
    @JsonProperty("column")
    protected String column;

    /** The maximum number of levels in the tree. */
    @JsonProperty("max_levels")
    private Integer maxLevels;

    /**
     * Sets the name of the Cassandra column to be mapped.
     *
     * @param column The name of the Cassandra column to be mapped.
     * @return This.
     */
    @SuppressWarnings("unchecked")
    public final GeoShapeMapperBuilder column(String column) {
        this.column = column;
        return this;
    }

    /**
     * Sets the maximum number of levels in the tree.
     *
     * @param maxLevels The maximum number of levels in the tree.
     * @return This
     */
    public GeoShapeMapperBuilder maxLevels(Integer maxLevels) {
        this.maxLevels = maxLevels;
        return this;
    }

    /**
     * Returns the {@link GeoShapeMapper} represented by this {@link MapperBuilder}.
     *
     * @param field The name of the field to be built.
     * @return The {@link GeoShapeMapper} represented by this.
     */
    @Override
    public GeoShapeMapper build(String field) {
        return new GeoShapeMapper(field, column, validated, maxLevels);
    }
}
