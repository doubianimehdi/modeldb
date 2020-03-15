#!/bin/bash

set -e

BASE="../protos/gen/swagger/protos/public/modeldb/versioning/VersioningService.swagger.json"
TARGET="src/main/java/ai/verta/modeldb/versioning/autogenerated"

rm -rf $TARGET

for f in $(find $BASE -type f | sort)
do
    echo "Processing $f"
    ../client/tools/swagger_codegen.py --input $f --output-dir $TARGET --templates templates --file-suffix java --case capital

    echo ""
done

for f in $(find $TARGET -type f | grep -v 'Blob.java' | grep -v 'Diff.java' | grep -v 'DiffStatusEnumDiffStatus.java')
do
    rm $f
done

for f in $(find $TARGET -type f)
do
    (cat $f | sed 's,Versioning,,g') > /tmp/foo
    rm $f
    mv /tmp/foo $(dirname $f)/$(basename $f | sed 's,^Versioning,,')
done

cat > $TARGET/_public/modeldb/versioning/model/DiffStatusEnumDiffStatus.java <<EOF
// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import ai.verta.modeldb.versioning.DiffStatusEnum.DiffStatus;

public class DiffStatusEnumDiffStatus {
    public DiffStatusEnum.DiffStatus Status;

    public DiffStatusEnumDiffStatus() {
        Status = DiffStatus.UNKNOWN;
    }

    static public DiffStatusEnumDiffStatus fromProto(ai.verta.modeldb.versioning.DiffStatusEnum.DiffStatus blob) {
        if (blob == null) {
            return null;
        }

        DiffStatusEnumDiffStatus obj = new DiffStatusEnumDiffStatus();
        obj.Status = blob;
        return obj;
    }

    public ai.verta.modeldb.versioning.DiffStatusEnum.DiffStatus toProto() {
        return this.Status;
    }

    public void preVisitShallow(Visitor visitor) throws ModelDBException {
        visitor.preVisitDiffStatusEnumDiffStatus(this);
    }

    public void preVisitDeep(Visitor visitor) throws ModelDBException {
        this.preVisitShallow(visitor);
    }

    public DiffStatusEnumDiffStatus postVisitShallow(Visitor visitor) throws ModelDBException {
        return visitor.postVisitDiffStatusEnumDiffStatus(this);
    }

    public DiffStatusEnumDiffStatus postVisitDeep(Visitor visitor) throws ModelDBException {
        return this.postVisitShallow(visitor);
    }
}
EOF

VISITOR="src/main/java/ai/verta/modeldb/versioning/blob/visitors/Visitor.java"

cat > $VISITOR <<EOF
package ai.verta.modeldb.versioning.blob.visitors;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Visitor {
EOF
for f in $(find $TARGET -type f | grep '\(Blob\|Diff\).java$' | sort)
do
    type=$(basename $f | sed 's,\.java$,,')
    cat >> $VISITOR <<EOF
    public void preVisitListOf${type}(List<${type}> lst) throws ModelDBException {
        for (${type} val : lst) {
            preVisit${type}(val);
        }
    }

    public void preVisitDeepListOf${type}(List<${type}> lst) throws ModelDBException {
        for (${type} val : lst) {
            preVisitDeep${type}(val);
        }
    }

    public List<${type}> postVisitListOf${type}(List<${type}> lst) throws ModelDBException {
        final List<${type}> collect = new ArrayList<>(lst.size());
        for (${type} val : lst) {
            collect.add(postVisit${type}(val));
        }
        return collect;
    }

    public List<${type}> postVisitDeepListOf${type}(List<${type}> lst) throws ModelDBException {
        final List<${type}> collect = new ArrayList<>(lst.size());
        for (${type} val : lst) {
            collect.add(postVisitDeep${type}(val));
        }
        return collect;
    }

    public void preVisit${type}(${type} blob) throws ModelDBException {}
    public void preVisitDeep${type}(${type} blob) throws ModelDBException {}
    public ${type} postVisit${type}(${type} blob) throws ModelDBException { return blob; }
    public ${type} postVisitDeep${type}(${type} blob) throws ModelDBException { return blob.postVisitDeep(this); }
EOF
done
for type in String Boolean Double Integer Long Float DiffStatusEnumDiffStatus
do
    cat >> $VISITOR <<EOF
    public void preVisitListOf${type}(List<${type}> lst) throws ModelDBException {
        for (${type} val : lst) {
            preVisit${type}(val);
        }
    }

    public void preVisitDeepListOf${type}(List<${type}> lst) throws ModelDBException {
        for (${type} val : lst) {
            preVisitDeep${type}(val);
        }
    }

    public List<${type}> postVisitListOf${type}(List<${type}> lst) throws ModelDBException {
        final List<${type}> collect = new ArrayList<>(lst.size());
        for (${type} val : lst) {
            collect.add(postVisit${type}(val));
        }
        return collect;
    }

    public List<${type}> postVisitDeepListOf${type}(List<${type}> lst) throws ModelDBException {
        final List<${type}> collect = new ArrayList<>(lst.size());
        for (${type} val : lst) {
            collect.add(postVisitDeep${type}(val));
        }
        return collect;
    }

    public void preVisit${type}(${type} blob) throws ModelDBException {}
    public void preVisitDeep${type}(${type} blob) throws ModelDBException {}
    public ${type} postVisit${type}(${type} blob) throws ModelDBException { return blob; }
    public ${type} postVisitDeep${type}(${type} blob) throws ModelDBException { return blob; }
EOF
done
cat >> $VISITOR <<EOF
}
EOF
