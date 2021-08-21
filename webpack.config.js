const path = require('path');
const webpack  = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const RefreshWebpackPlugin  = require('@pmmmwh/react-refresh-webpack-plugin');
const devMode = process.env.NODE_ENV !== 'production'


module.exports = {
    name: 'heartattack',
    mode: 'development',
    devtool: 'eval',
    resolve: {
        extensions: ['.js','.jsx','.css']
    },
    entry: {
        app: ['./src/index'],
    },
    plugins: [
        new MiniCssExtractPlugin({
            // Options similar to the same options in webpackOptions.output
            // both options are optional
            filename: devMode ? '[name].css' : '[name].[hash].css',
            chunkFilename: devMode ? '[id].css' : '[id].[hash].css',
          }),
        new RefreshWebpackPlugin,
        new webpack.HotModuleReplacementPlugin(),
      ],
    
    module: {
        rules: [{
            test: /\.jsx?/,
            loader: 'babel-loader',
            options: {
                presets: ['@babel/preset-env', '@babel/preset-react'],
                plugins: ['@babel/plugin-proposal-class-properties', 'react-refresh/babel'],
            }
        }, {
            test: /\.(sa|sc|c)ss$/,
            use: [
            devMode ? 'style-loader' : MiniCssExtractPlugin.loader,
            'css-loader',
            'postcss-loader',
            'sass-loader',
            ],
        }, {
            test: /\.(gif|svg|jpg|png)$/,
            loader: 'file-loader',
            options: {
                name: 'assets/[contenthash].[ext]'
            }
        }]
    },
    
    output: {
        filename: 'app.js',
        path: path.join(__dirname, 'dist'),
        publicPath: '/dist/'
    },
    devServer: {
        historyApiFallback: true,
        publicPath: '/dist/',
        hot: true,
    },

};

// 라우트를 사용하기 위한 웹팩 설정 : https://stackoverflow.com/questions/43209666/react-router-v4-cannot-get-url