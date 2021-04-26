'use strict'
const utils = require('./utils')
const config = require('../config')
const isProduction = process.env.NODE_ENV === 'production'
const sourceMapEnabled = isProduction
  ? config.build.productionSourceMap
  : config.dev.cssSourceMap

module.exports = {
  loaders: utils.cssLoaders({
    sourceMap: sourceMapEnabled,
    extract: isProduction
  }),
  cssSourceMap: sourceMapEnabled,
  cacheBusting: config.dev.cacheBusting,
  transformToRequire: {
    video: ['src', 'poster'],
    source: 'src',
    img: 'src',
    image: 'xlink:href'
  },
  chainWebpack: config => {
    const fileRule = config.module.rule('file');
    fileRule.uses.clear();
    fileRule
      .test(/\.pdf|ico$/)
      .use('file-loader')
      .loader('file-loader')
      .options({
        limit: 10000,
      })
  },
  publicPath: './'
};
