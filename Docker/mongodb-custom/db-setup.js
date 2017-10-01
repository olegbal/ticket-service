let error = false;

let dbName="ticket_service";

db = db.getSiblingDB(dbName);

let res = [
    db.createUser({
            user: "user",
            pwd: "user123",
            roles: [ { role: "dbOwner", db: dbName }]
        }),
];

printjson(res);

if (error) {
    print('Error, exiting');
    quit(1);
}