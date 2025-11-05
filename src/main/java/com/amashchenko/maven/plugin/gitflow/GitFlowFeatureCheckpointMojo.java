/*
 * Copyright 2014-2024 Aleksandr Mashchenko.
 * Portions Copyright © 2024-2025 Integrated Knowledge Management (support@ikm.dev)
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
package com.amashchenko.maven.plugin.gitflow;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The git flow feature checkpoint mojo.
 * Wraps GitFlowFeatureFinishMojo with keepBranch=true and requires separateFinishBranches=true.
 *
 * @since 1.21.1-SNAPSHOT
 */
@Mojo(name = "feature-checkpoint", aggregator = true)
public class GitFlowFeatureCheckpointMojo extends GitFlowFeatureFinishMojo {

    /** {@inheritDoc} */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        // Validate that separateFinishBranches is set to true
        if (!separateFinishBranches) {
            throw new MojoFailureException(
                    "feature-checkpoint goal requires separateFinishBranches to be set to true. " +
                    "Please review the plugin configuration or run with -DseparateFinishBranches=true");
        }

        // Call the parent GitFlowFeatureFinishMojo execute method with keepBranch set to true
        keepBranch = true;
        super.execute();
    }
}
