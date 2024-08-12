package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AlgoutilsLibraryAccessors laccForAlgoutilsLibraryAccessors = new AlgoutilsLibraryAccessors(owner);
    private final JunitLibraryAccessors laccForJunitLibraryAccessors = new JunitLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Dependency provider for <b>annotations</b> with <b>org.jetbrains:annotations</b> coordinates and
     * with version <b>23.0.0</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getAnnotations() {
        return create("annotations");
    }

    /**
     * Group of libraries at <b>algoutils</b>
     */
    public AlgoutilsLibraryAccessors getAlgoutils() {
        return laccForAlgoutilsLibraryAccessors;
    }

    /**
     * Group of libraries at <b>junit</b>
     */
    public JunitLibraryAccessors getJunit() {
        return laccForJunitLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AlgoutilsLibraryAccessors extends SubDependencyFactory {

        public AlgoutilsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>student</b> with <b>org.tudalgo:algoutils-student</b> coordinates and
         * with version reference <b>algoutils</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStudent() {
            return create("algoutils.student");
        }

        /**
         * Dependency provider for <b>tutor</b> with <b>org.tudalgo:algoutils-tutor</b> coordinates and
         * with version reference <b>algoutils</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTutor() {
            return create("algoutils.tutor");
        }

    }

    public static class JunitLibraryAccessors extends SubDependencyFactory {

        public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.junit.jupiter:junit-jupiter</b> coordinates and
         * with version <b>5.9.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("junit.core");
        }

        /**
         * Dependency provider for <b>pioneer</b> with <b>org.junit-pioneer:junit-pioneer</b> coordinates and
         * with version <b>2.0.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPioneer() {
            return create("junit.pioneer");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>algoutils</b> with value <b>0.5.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAlgoutils() { return getVersion("algoutils"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final JagrPluginAccessors paccForJagrPluginAccessors = new JagrPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>style</b> with plugin id <b>org.sourcegrade.style</b> and
         * with version <b>2.1.0</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getStyle() { return createPlugin("style"); }

        /**
         * Group of plugins at <b>plugins.jagr</b>
         */
        public JagrPluginAccessors getJagr() {
            return paccForJagrPluginAccessors;
        }

    }

    public static class JagrPluginAccessors extends PluginFactory {

        public JagrPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>jagr.gradle</b> with plugin id <b>org.sourcegrade.jagr-gradle</b> and
         * with version <b>0.10.3</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getGradle() { return createPlugin("jagr.gradle"); }

    }

}
