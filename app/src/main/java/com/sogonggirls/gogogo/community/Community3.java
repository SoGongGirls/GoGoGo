package com.sogonggirls.gogogo.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sogonggirls.gogogo.R;

public class Community3 extends Fragment {

//    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
//
//    private ListView mPostView;
//    private PostAdapter mAdapter;
//    private List<PostItem> mDatas;
//
//    FloatingActionButton community_write;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.community1, container, false);
//
//        /* 객체 초기화 */
//        community_write = (FloatingActionButton) view.findViewById(R.id.community_write);
//        mPostView = (ListView) view.findViewById(R.id.community_list1);
//        mDatas = new ArrayList<>();
//
//        /* 글 작성 버튼 클릭 */
//        community_write.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Community_insert1.class);
//                intent.putExtra("communitynumber", 3);
//                startActivity(intent);
//            }
//        });
//
//        //리스트 객체 클릭
//        mPostView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getContext(), PostingActivity.class);
//                PostItem item = (PostItem) mAdapter.getItem(i);
//                String doc_id = String.valueOf(item.getPostId());
//
//                intent.putExtra("comu_id", 3);
//                intent.putExtra("doc_id", doc_id);
//                startActivity(intent);
//            }
//        });


        return view;

    }// OnCreateView
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mStore.collection(FirebaseID.tips)
//                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() { //실시간 정렬/단점 새로안불러오고 추가로 불러온다.
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
//                        if (queryDocumentSnapshots != null) {
//                            mDatas.clear();//단점 해결용 초기화
//                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
//                                Map<String, Object> shot = snap.getData();
//                                String postId = snap.getId();
//                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
//                                String nickname = String.valueOf(shot.get(FirebaseID.nickname));
//                                String title = String.valueOf(shot.get(FirebaseID.title));
//                                String contents = String.valueOf(shot.get(FirebaseID.contents));
//
//                                String temp = String.valueOf(shot.get(FirebaseID.timestamp));
//                                temp = temp.replace("Timestamp(seconds=", "").replace(" nanoseconds=", "").replace(")", "");
//                                String[] array = temp.split(",");
//                                String tai = array[0] ;
//                                PostItem data = new PostItem(documentId, nickname, title, contents, postId, tai);
//                                mDatas.add(data);
//                            }
//                            mAdapter = new PostAdapter(mDatas);
//                            mPostView.setAdapter((ListAdapter) mAdapter);
//                        }
//
//                    }
//                });
//    }
}