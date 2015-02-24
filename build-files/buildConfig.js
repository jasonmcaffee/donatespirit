var _ = require("underscore");
var deamdify = require("deamdify");
var reactify = require('reactify');
/**
 * generates the build configuration
 *
 * browserify config https://github.com/substack/node-browserify#var-b--browserifyfiles-or-opts
 *
 */
function generateBuildConfig(){
    var jsBaseDir = './src/main/webapp/resources/js';
    var lessBaseDir = './src/main/webapp/resources/css';

    var buildConfig={
        jsBaseDir: jsBaseDir,
        jsDistDir: './target/resources/js',
        lessDistDir: './target/resources/css',
        lessBaseDir: lessBaseDir,
        //tell browserify to scan the js directory so we don't have to use relative paths when referencing modules (e.g. no ../components).
        paths:['./src/main/webapp/resources/js'],
        //assume these extensions for require statements so we don't have to include in requires e.g. components/header vs components/header.jsx
        extensions:['.js','.jsx'],

        //http://lisperator.net/uglifyjs/codegen
        uglify:{
            mangle:true,
            output:{  //http://lisperator.net/uglifyjs/codegen
                beautify: false,
                quote_keys: true  //this is needed because when unbundling/browser unpack, the defined modules need to be strings or we wont know what their names are.
            }
            //compress:false // we can refine if needed. http://lisperator.net/uglifyjs/compress
        }
    };


    return buildConfig;
}

var buildConfig = generateBuildConfig();
//console.log('generated build config: ' + JSON.stringify(buildConfig, null, 4));

var commonBrowserifyConfig = {
    //tell browserify to scan the js directory so we don't have to use relative paths when referencing modules (e.g. no ../components).
    paths:buildConfig.paths,
    //tell browserify to scan the js directory so we don't have to use relative paths when referencing modules (e.g. no ../components).
    extensions:buildConfig.extensions,
    //compile jsx to normal js, then convert amd defines to commonjs defines
    transform: [reactify, deamdify]
};

module.exports = {
    config: buildConfig,
    commonBrowserifyConfig: commonBrowserifyConfig,
    /**
     * convenience function so we don't have to keep defining paths, transforms, etc.
     * @param browserifyConfig
     * @returns {*}
     */
    mixinCommonBrowserifyConfig:function(browserifyConfig){
        return _.extend({}, commonBrowserifyConfig, browserifyConfig);
    }
};