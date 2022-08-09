# Deploy iLender as OCP Application

<img src="../docs/design/iLender-Microservices.png">

## 1. Parameters

 Application parameters are available in the below file. But it is not requried to change anything. It can have default values as it is.

[./01-ocp/01-all-microsevices/yaml/20-deployable-common.yaml ](./01-ocp/01-all-microsevices/yaml/20-deployable-common.yaml) 


## 2. Deploy app

Run the below command to deploy the app.

```
cd 01-ocp/01-all-microsevices/install

sh 01-install.sh
```

The application is deployed in `ilender-ocp-ns` namespace

## 3. Get Routes

Run the below command to get the routes.

```
oc get routes -n ilender-ocp-ns
```

Take the url of the `ilender-frontweb` route.

The url would be like this.
```
http://ilender-frontweb-ilender-ocp-ns.abcd.us-south.containers.appdomain.cloud/
```

## 4. Access the app

Using the above url, access the app with the below users.

```
Business Manager : sam/sam
SMB Customers : sandy/sandy
```